package za.co.paygenius.developer.paymentservice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsZAR.CreatePaymentRequest;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsZAR.CreatePaymentResponse;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsZAR.ExecutePaymentRequest;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsZAR.PartialRefundRequest;
import za.co.paygenius.developer.paymentservice.dto.TransactionResponse;
import za.co.paygenius.developer.paymentservice.service.PayGeniusService;

import java.util.Map;
@RestController
public class CardPaymentZARController {
    @Autowired
    private PayGeniusService payGeniusService;

    @PostMapping(path = "/create-payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatePaymentResponse> createPayment(@Valid @RequestBody CreatePaymentRequest request, @RequestHeader Map<String, String> headers) throws Exception {
        return ResponseEntity.ok(payGeniusService.createPayment(request, headers));
    }

    @PostMapping(path = "/partial-refund/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> partialRefund(@Valid@RequestBody PartialRefundRequest request, @RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(payGeniusService.partialRefund(request, headers,reference));
    }

    @GetMapping(path = "/full-refund/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> fullRefund(@RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(payGeniusService.fullRefund(headers,reference));
    }

    @GetMapping(path = "/execute-transaction/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> executeTransaction(@RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(payGeniusService.executeTransaction(headers,reference));
    }

    @PostMapping(path = "/execute-part-transaction/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> executePartTransaction(@Valid@RequestBody ExecutePaymentRequest request, @RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(payGeniusService.executePartTransaction(request, headers,reference));
    }
}
