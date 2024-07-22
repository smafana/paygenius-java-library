package za.co.paygenius.developer.paygeniusjavalibrary.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.requests.*;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.responses.CreatePaymentOtherResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.responses.EditPaymentResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.TransactionResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.service.CardPaymentOtherService;

import java.util.Map;

@RestController
public class CardPaymentOtherController {

    @Autowired
    private CardPaymentOtherService cardPaymentOtherService;

    @PostMapping(path = "/create-payment-authorized", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatePaymentOtherResponse> createPaymentAuthorized(@Valid @RequestBody CreatePaymentOtherRequest request, @RequestHeader Map<String,String> headers) throws Exception{
        return ResponseEntity.ok(cardPaymentOtherService.createPaymentWithAuthorize(request, headers));
    }

    @PostMapping(path = "/execute-partial-payment/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> executePartialPayment(@Valid @RequestBody ExecutePaymentOtherRequest request, @RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(cardPaymentOtherService.partialExecutePaymentOther(request, headers, reference));
    }

    @GetMapping(path = "/execute-payment/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> executePayment( @RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(cardPaymentOtherService.executePaymentOther( headers, reference));
    }

    @PostMapping(path = "/partial-refund-payment/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> partialRefundPayment(@Valid @RequestBody RefundPaymentOtherRequest request, @RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(cardPaymentOtherService.partialRefundPaymentOther(request, headers, reference));
    }

    @GetMapping(path = "/full-refund-payment/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> fullRefundPayment( @RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(cardPaymentOtherService.fullRefundPaymentOther( headers, reference));
    }

    @PostMapping(path = "/edit-payment/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EditPaymentResponse> editPayment(@Valid @RequestBody EditPaymentRequest request, @RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(cardPaymentOtherService.editPayment(request, headers, reference));
    }

    @PostMapping(path = "/edit-payment-advanced/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EditPaymentResponse> editPaymentAdvanced(@Valid @RequestBody EditPaymentAdvancedRequest request, @RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(cardPaymentOtherService.editPaymentAdvanced(request, headers, reference));
    }
}
