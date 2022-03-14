package com.lbg.open_banking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Brands {
    @JsonProperty("Brand")
    public List<Brand> brand;
}
