package com.lbg.open_banking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Location {

    @JsonProperty("PostalAddress")
    public PostalAddress postalAddress;
}
