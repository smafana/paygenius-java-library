package za.co.paygenius.developer.paymentservice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsOther.CreatePaymentOtherRequest;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsOther.Response;
import za.co.paygenius.developer.paymentservice.dto.instantMethodsEFT.CreateEFTPaymentRequest;
import za.co.paygenius.developer.paymentservice.dto.instantMethodsEFT.CreateEFTPaymentResponse;
import za.co.paygenius.developer.paymentservice.service.CreatePaymentOtherService;

import java.util.Map;

@RestController
public class CardPaymentOtherController {

    @Autowired
    private CreatePaymentOtherService createPaymentOtherService;

    @PostMapping(path = "/create-payment-authorized", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> createPaymentAuthorized(@Valid @RequestBody CreatePaymentOtherRequest request, @RequestHeader Map<String,String> headers) throws Exception{
        return ResponseEntity.ok(createPaymentOtherService.createPaymentWithAuthorize(request, headers));
    }
}
