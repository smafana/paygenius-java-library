package za.co.paygenius.developer.paymentservice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsOther.CreatePaymentOtherRequest;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsOther.CreatePaymentOtherResponse;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsOther.ExecutePaymentOtherRequest;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsOther.RefundPaymentOtherRequest;
import za.co.paygenius.developer.paymentservice.dto.TransactionResponse;
import za.co.paygenius.developer.paymentservice.service.CreatePaymentOtherService;

import java.util.Map;

@RestController
public class CardPaymentOtherController {

    @Autowired
    private CreatePaymentOtherService createPaymentOtherService;

    @PostMapping(path = "/create-payment-authorized", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatePaymentOtherResponse> createPaymentAuthorized(@Valid @RequestBody CreatePaymentOtherRequest request, @RequestHeader Map<String,String> headers) throws Exception{
        return ResponseEntity.ok(createPaymentOtherService.createPaymentWithAuthorize(request, headers));
    }

    @PostMapping(path = "/execute-partial-payment/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> executePartialPayment(@Valid @RequestBody ExecutePaymentOtherRequest request, @RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(createPaymentOtherService.partialExecutePaymentOther(request, headers, reference));
    }

    @GetMapping(path = "/execute-payment/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> executePayment( @RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(createPaymentOtherService.executePaymentOther( headers, reference));
    }

    @PostMapping(path = "/partial-refund-payment/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> partialRefundPayment(@Valid @RequestBody RefundPaymentOtherRequest request, @RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(createPaymentOtherService.partialRefundPaymentOther(request, headers, reference));
    }

    @GetMapping(path = "/full-refund-payment/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> fullRefundPayment( @RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(createPaymentOtherService.fullRefundPaymentOther( headers, reference));
    }
}
