package com.lbg.open_banking.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MatchingError {

    String httpCode;
    String httpMessage;
    String moreInformation;
}
