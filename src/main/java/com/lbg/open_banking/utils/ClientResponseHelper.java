package com.lbg.open_banking.utils;


import lombok.Data;

@Data
public class ClientResponseHelper <T>{

    private T data;
    private Boolean success;
    private String message;

}
