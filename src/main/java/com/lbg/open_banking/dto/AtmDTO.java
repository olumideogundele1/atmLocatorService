package com.lbg.open_banking.dto;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AtmDTO {

    @NotBlank
    private String url;


    @NotBlank(message = "Required")
    @NotEmpty(message = "Not empty")
    @NotNull(message = "nOt null")
    private String identification;
}
