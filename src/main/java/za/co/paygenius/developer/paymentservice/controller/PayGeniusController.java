package za.co.paygenius.developer.paymentservice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.paygenius.developer.paymentservice.dto.*;
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

    @PostMapping(path = "/create-eft-payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateEFTPaymentResponse> createEFTPayment(@Valid @RequestBody CreateEFTPaymentRequest request, @RequestHeader Map<String,String> headers) throws Exception{
        return ResponseEntity.ok(payGeniusService.createPaymentEFT(request, headers));
    }

    @PostMapping(path = "/partial-refund/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RefundResponse> partialRefund(@Valid@RequestBody PartialRefundRequest request, @RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(payGeniusService.partialRefund(request, headers,reference));
    }

    @GetMapping(path = "/full-refund/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RefundResponse> fullRefund(@RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(payGeniusService.fullRefund(headers,reference));
    }
}
