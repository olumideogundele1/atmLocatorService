package com.lbg.open_banking.service;

import com.lbg.open_banking.dto.ATM;
import com.lbg.open_banking.utils.ClientResponseHelper;

public interface AtmService {
     ClientResponseHelper<ATM> getAtmById(String url, String identification) throws Exception;
}
