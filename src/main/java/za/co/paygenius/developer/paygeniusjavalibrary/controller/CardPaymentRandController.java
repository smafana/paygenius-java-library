package za.co.paygenius.developer.paygeniusjavalibrary.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.requests.CreatePaymentRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.responses.CreatePaymentResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.requests.ExecutePaymentRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.requests.PartialRefundRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.TransactionResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.service.CardPaymentRandService;

import java.util.Map;
@RestController
public class CardPaymentRandController {

    @Autowired
    private CardPaymentRandService cardPaymentRandService;

    @PostMapping(path = "/create-payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatePaymentResponse> createPayment(@Valid @RequestBody CreatePaymentRequest request, @RequestHeader Map<String, String> headers) throws Exception {
        return ResponseEntity.ok(cardPaymentRandService.createPayment(request));
    }

    @PostMapping(path = "/partial-refund/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> partialRefund(@Valid@RequestBody PartialRefundRequest request, @RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(cardPaymentRandService.partialRefund(request, headers,reference));
    }

    @GetMapping(path = "/full-refund/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> fullRefund(@RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(cardPaymentRandService.fullRefund(headers,reference));
    }

    @GetMapping(path = "/execute-transaction/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> executeTransaction(@RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(cardPaymentRandService.executeTransaction(headers,reference));
    }

    @PostMapping(path = "/execute-part-transaction/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> executePartTransaction(@Valid@RequestBody ExecutePaymentRequest request, @RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(cardPaymentRandService.executePartTransaction(request, headers,reference));
    }
}
