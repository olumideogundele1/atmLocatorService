package com.lbg.open_banking.service;

import com.lbg.open_banking.dto.ATM;
import com.lbg.open_banking.utils.ClientResponseHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AtmServiceImplTest {

    @Autowired
    AtmServiceImpl atmService;

    @Before()
    public void init(){
        ATM atm = new ATM();
        atm.setIdentification("LFFFBC11");

    }

    @Test
    public void getAtmById() throws Exception {
        String url = "https://api.lloydsbank.com/open-banking/v2.2/atms";
        String id = "LFFFBC11";
        String expected = "LFFFBC11";
        ClientResponseHelper<ATM> atm = atmService.getAtmById(url,id);

        assertEquals(expected,atm.getData().identification);
    }
}