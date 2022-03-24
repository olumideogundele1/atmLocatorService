package com.lbg.open_banking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ATM {
    @JsonProperty("Identification")
    public String identification;
    @JsonProperty("SupportedCurrencies")
    public List<String> supportedCurrencies;
    @JsonProperty("Location")
    public Location location;
}
