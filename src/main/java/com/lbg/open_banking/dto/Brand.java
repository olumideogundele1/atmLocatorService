package com.lbg.open_banking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Brand {
    @JsonProperty("BrandName")
    public String brandName;
    @JsonProperty("ATM")
    public List<ATM> atm;
}
