package com.lbg.open_banking.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbg.open_banking.dto.ATM;
import com.lbg.open_banking.dto.Brand;
import com.lbg.open_banking.dto.Brands;
import com.lbg.open_banking.dto.ResponseData;
import com.lbg.open_banking.exception.CustomErrorAdvice;
import com.lbg.open_banking.exception.CustomNotFoundException;
import com.lbg.open_banking.utils.ClientResponseHelper;
import com.lbg.open_banking.utils.MatchingError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class AtmServiceImpl implements AtmService{
    Logger logger = LoggerFactory.getLogger(AtmServiceImpl.class);
    private static final String LLOYD_APPLICATION_JSON = "application/prs.openbanking.opendata.v2.2+json";
    private String ERROR_MSG = "We are temporarily unable to complete your request. Please try again later";


    RestTemplate restTemplate;

    public AtmServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ClientResponseHelper<ATM> getAtmById(String url, String identification) throws Exception {
        logger.info("url"+ url);
        logger.info("ide"+ identification);
        ClientResponseHelper<ATM> responseHelper = new ClientResponseHelper<>();
        responseHelper.setSuccess(Boolean.FALSE);
        try{
            HttpEntity<?> httpEntity = new HttpEntity<>(getHttpHeaders());
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
            builder.build(true);
            ResponseEntity<ResponseData> response = this.restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, ResponseData.class);
            if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                List<ATM> atmList = response.getBody().getData().get(0).getBrand().get(0).getAtm();
                Optional<ATM> optionalATM = atmList.parallelStream()
                        .filter(atm -> atm.identification.equals(identification))
                        .findFirst();
                if (optionalATM.isPresent()){
                    logger.info(optionalATM.get().toString());
                    responseHelper.setData(optionalATM.get());
                    responseHelper.setSuccess(Boolean.TRUE);
                    responseHelper.setMessage("Success");
                }
                else{
                    logger.info("NOT FOUND");
                    throw new CustomNotFoundException("ATM not found for identification "+ identification);
                }

            }else{
                logger.info("Error" +response.getBody());
            }

           return responseHelper;
        }catch (Exception e){
            logger.error("Exception "+ e.getMessage());
            throw new CustomNotFoundException(e.getMessage());
        }
    }


    public HttpHeaders getHttpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("accept",LLOYD_APPLICATION_JSON);
        headers.add("If-Modified-Since","REPLACE_THIS_VALUE");
        headers.add("If-None-Match","REPLACE_THIS_VALUE");
        return headers;

    }

    private <T> ClientResponseHelper<T> getResponse(Class<T> clazz, Map<String,Object> rsp){
        ClientResponseHelper<T> response = new ClientResponseHelper<>();
//        logger.info("Resppp ====> " + rsp.get(dataField));
//        if(!Strings.isNullOrEmpty(dataField) || rsp.containsKey(dataField))
        response.setData(new ObjectMapper().convertValue(rsp,clazz));
        Boolean success = Boolean.parseBoolean(rsp.getOrDefault("successful",false).toString());
        response.setSuccess(success && rsp.getOrDefault("responseCode","EE").equals("00"));
        response.setMessage(rsp.getOrDefault("responseMessage",ERROR_MSG).toString());
        return response;
    }
}
