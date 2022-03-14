package com.lbg.open_banking.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AtmDTO {

    @NotBlank
    private String url;

    @NotBlank
    private String identification;
}
