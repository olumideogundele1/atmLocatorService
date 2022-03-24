package com.lbg.open_banking.controller;

import com.lbg.open_banking.OpenBankingApplication;
import com.lbg.open_banking.dto.AtmDTO;
import com.lbg.open_banking.utils.ClientResponseHelper;
import org.json.JSONException;
import org.json.JSONObject;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

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


        String atmDTO1 = "{ \\\"url\\\":\\\"https://api.lloydsbank.com/open-banking/v2.2/atms\\\", \\\"identification\\\":\\\"LFFFBC11\\\"\\}";


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url","https://api.lloydsbank.com/open-banking/v2.2/atms");
        jsonObject.put("identification","LFFFBC11");

        HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);
//        HttpEntity<String> entity = new HttpEntity<>(atmDTO.toString(),headers);
        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("/api/v1/atm/get-atm"),HttpMethod.POST, entity,String.class);
//        assertEquals(expected, response.getBody());
        assertEquals(200, response.getStatusCode().value());
    }

    private URI createURLWithPort(String uri) {
        return URI.create("http://localhost:" + port + uri);
    }

    @Test
    public void notValid_ShouldReturnWith400() throws JSONException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url","https://api.lloydsbank.com/open-banking/v2.2/atms");
        jsonObject.put("id","LFFFBC11");

        HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);
//        HttpEntity<String> entity = new HttpEntity<>(atmDTO.toString(),headers);
        ResponseEntity<?> response = restTemplate.exchange(createURLWithPort("/api/v1/atm/get-atm"),HttpMethod.POST, entity,String.class);
//        assertEquals(expected, response.getBody());
        assertEquals(400, response.getStatusCode().value());
    }


    @Test
    public void getAtmLocation() {
    }
}