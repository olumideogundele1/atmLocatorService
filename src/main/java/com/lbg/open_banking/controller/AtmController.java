package com.lbg.open_banking.controller;


import com.lbg.open_banking.dto.ATM;
import com.lbg.open_banking.dto.AtmDTO;
import com.lbg.open_banking.exception.CustomNotFoundException;
import com.lbg.open_banking.service.AtmService;
import com.lbg.open_banking.utils.ClientResponseHelper;
import com.lbg.open_banking.utils.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/atm")
public class AtmController {

    AtmService atmService;

    public AtmController(AtmService atmService) {
        this.atmService = atmService;
    }

//    @Operation(summary = "Get a ATM by its identification")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Found the ATM Location",
//                    content = { @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = ATM.class)) }),
//            @ApiResponse(responseCode = "400", description = "Invalid identification/url supplied",
//                    content = {@Content(mediaType = "application/json",
//                            schema = @Schema(implementation = ErrorResponse.class))}),
//            @ApiResponse(responseCode = "404", description = "ATM Location not found",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = ErrorResponse.class))) })
    @GetMapping("/get-atm")
    public ResponseEntity<ClientResponseHelper<ATM>> getAtmLocation(@RequestBody AtmDTO atmDTO){
        try{
            return ResponseEntity.ok().body(atmService.getAtmById(atmDTO.getUrl(),atmDTO.getIdentification()));
        }catch (Exception e){
            throw new CustomNotFoundException(e.getMessage());
        }
    }

}
