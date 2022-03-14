package com.lbg.open_banking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ATM {
    @JsonProperty("Identification")
    public String identification;
    @JsonProperty("SupportedCurrencies")
    public List<String> supportedCurrencies;
    @JsonProperty("Location")
    public Location location;
}
