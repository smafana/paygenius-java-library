package za.co.paygenius.developer.paygeniusjavalibrary.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.instantMethodsEFT.requests.CreateEFTPaymentRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.instantMethodsEFT.responses.CreateEFTPaymentResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.service.InstantEFTMethodsService;
import za.co.paygenius.developer.paygeniusjavalibrary.service.GenerateSignature;

import java.util.Map;

@RestController
public class InstantEFTController {
    @Autowired
    private GenerateSignature payGeniusService;
    @Autowired
    private InstantEFTMethodsService instantEFTMethodsService;

    @PostMapping(path = "/create-eft-payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateEFTPaymentResponse> createEFTPayment(@Valid @RequestBody CreateEFTPaymentRequest request, @RequestHeader Map<String,String> headers) throws Exception{
        return ResponseEntity.ok(instantEFTMethodsService.createPaymentEFT(request, headers));
    }





}
