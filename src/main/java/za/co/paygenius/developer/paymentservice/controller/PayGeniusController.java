package za.co.paygenius.developer.paymentservice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import za.co.paygenius.developer.paymentservice.dto.CreatePaymentRequest;
import za.co.paygenius.developer.paymentservice.dto.CreatePaymentResponse;
import za.co.paygenius.developer.paymentservice.service.PayGeniusService;

import java.util.Map;

@RestController
public class PayGeniusController {
    @Autowired
    private PayGeniusService payGeniusService;

    @PostMapping(path = "/create-payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatePaymentResponse> createPayment(@Valid @RequestBody CreatePaymentRequest request, @RequestHeader Map<String, String> headers) throws Exception {
        return ResponseEntity.ok(payGeniusService.createPayment(request, headers));
    }
}
