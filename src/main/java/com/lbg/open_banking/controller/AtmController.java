package com.lbg.open_banking.controller;


import com.lbg.open_banking.dto.ATM;
import com.lbg.open_banking.dto.AtmDTO;
import com.lbg.open_banking.exception.CsutomBadException;
import com.lbg.open_banking.exception.CustomNotFoundException;
import com.lbg.open_banking.service.AtmService;
import com.lbg.open_banking.utils.ClientResponseHelper;
import com.lbg.open_banking.utils.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1/atm")
public class AtmController {
    Logger log = LoggerFactory.getLogger(AtmController.class);

    AtmService atmService;

    public AtmController(AtmService atmService) {
        this.atmService = atmService;
    }

    @Operation(summary = "Get a ATM by its identification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the ATM Location",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ATM.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid identification/url supplied",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "ATM Location not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))) })
    @PostMapping("/get-atm")
    public ResponseEntity<ClientResponseHelper<ATM>> getAtmLocation(@RequestBody AtmDTO atmDTO){
        log.info("IN here");
        if(StringUtils.isEmpty(atmDTO.getIdentification()) || StringUtils.isEmpty(atmDTO.getIdentification())){
            throw new CsutomBadException("Request is null");
        }
        try{
            return ResponseEntity.ok().body(atmService.getAtmById(atmDTO.getUrl(),atmDTO.getIdentification()));
        }catch (Exception e){
            throw new CustomNotFoundException(e.getMessage());
        }
    }

        @Operation(summary = "Get a ATM by its identification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the ATM Location",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ATM.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid identification/url supplied",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "ATM Location not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))) })
    @GetMapping("/get-atm")
    public ResponseEntity<ClientResponseHelper<ATM>> getAtmLocation(@RequestParam String url,@RequestParam String id){
        log.info("IN here");
        try{

            return ResponseEntity.ok().body(atmService.getAtmById(url,id));
        }catch (Exception e){
            throw new CustomNotFoundException(e.getMessage());
        }
    }

}
