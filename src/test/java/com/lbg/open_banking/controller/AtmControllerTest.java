package com.lbg.open_banking.controller;

import com.lbg.open_banking.OpenBankingApplication;
import com.lbg.open_banking.dto.AtmDTO;
import com.lbg.open_banking.utils.ClientResponseHelper;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OpenBankingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AtmControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    HttpHeaders headers = new HttpHeaders();

    @Before
    public void setUp() {

        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void validID_shouldReturnWith200()  throws JSONException {


        String atmDTO1 = "{\"url\":\" https:\\//api.lloydsbank.com\\/open-banking\\/v2.2\\/atms\", \"identification\":\"LFFFBC11\"}";
       // AtmDTO atmDTO = new AtmDTO();
      //  atmDTO.setUrl("https://api.lloydsbank.com/open-banking/v2.2/atms");
       // atmDTO.setIdentification("LFFFBC11");
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
       // map.add("url","https://api.lloydsbank.com/open-banking/v2.2/atms");
       // map.add("identification","LFFFBC11");
       // HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        HttpEntity<String> entity = new HttpEntity<String>(atmDTO1, headers);
        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("/api/v1/atm/get-atm"), HttpMethod.GET, entity, ClientResponseHelper.class);
//        assertEquals(expected, response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }

    private URI createURLWithPort(String uri) {
        return URI.create("http://localhost:" + port + uri);
    }


    @Test
    public void getAtmLocation() {
    }
}