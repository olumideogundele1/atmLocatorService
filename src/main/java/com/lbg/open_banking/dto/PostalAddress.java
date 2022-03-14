package com.lbg.open_banking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PostalAddress {
    @JsonProperty("AddressLine")
    public List<String> addressLine;
    @JsonProperty("StreetName")
    public String streetName;
    @JsonProperty("TownName")
    public String townName;
    @JsonProperty("CountrySubDivision")
    public List<String> countrySubDivision;
    @JsonProperty("Country")
    public String country;
    @JsonProperty("PostCode")
    public String postCode;
}
