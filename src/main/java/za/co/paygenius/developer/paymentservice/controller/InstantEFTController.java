package za.co.paygenius.developer.paymentservice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.paygenius.developer.paymentservice.dto.instantMethodsEFT.CreateEFTPaymentRequest;
import za.co.paygenius.developer.paymentservice.dto.instantMethodsEFT.CreateEFTPaymentResponse;
import za.co.paygenius.developer.paymentservice.service.PayGeniusService;

import java.util.Map;

@RestController
public class InstantEFTController {
    @Autowired
    private PayGeniusService payGeniusService;

    @PostMapping(path = "/create-eft-payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateEFTPaymentResponse> createEFTPayment(@Valid @RequestBody CreateEFTPaymentRequest request, @RequestHeader Map<String,String> headers) throws Exception{
        return ResponseEntity.ok(payGeniusService.createPaymentEFT(request, headers));
    }





}
