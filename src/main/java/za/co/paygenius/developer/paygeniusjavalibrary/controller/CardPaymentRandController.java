package za.co.paygenius.developer.paygeniusjavalibrary.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.requests.*;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.responses.ChallengedResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.responses.CreatePaymentResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.responses.NonChallengedResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.responses.Response3DSecureV1;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.TransactionResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.service.PayGeniusService;

import java.util.Map;
@RestController
public class CardPaymentRandController {

    @Autowired
    private PayGeniusService payGeniusService;

    @PostMapping(path = "/create-payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatePaymentResponse> createPayment(@Valid @RequestBody CreatePaymentRequest request, @RequestHeader Map<String, String> headers) throws Exception {
        return ResponseEntity.ok(payGeniusService.createPayment(request,headers));
    }

    @PostMapping(path = "/execute-part-transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> executePartPayment(@Valid @RequestBody ExecutePaymentRequest request, @RequestHeader Map<String, String> headers) throws Exception {
        return ResponseEntity.ok(payGeniusService.executePartPayment(request,headers));
    }

    @GetMapping(path = "/execute-transaction/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> executeTransaction(@RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(payGeniusService.executeFullPayment(headers,reference));
    }

    //bad request(400)
    @PostMapping(path = "/partial-refund", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> partRefund(@Valid @RequestBody PartialRefundRequest request, @RequestHeader Map<String, String> headers) throws Exception {
        return ResponseEntity.ok(payGeniusService.partRefund(request,headers));
    }

    @GetMapping(path = "/full-refund/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> fullrefund(@RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(payGeniusService.fullRefund(headers,reference));
    }

    @PostMapping(path = "/secure-3D-v1/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response3DSecureV1> secureV1(@Valid @RequestBody Request3DSecureV1 request, @RequestHeader Map<String, String> headers, @PathVariable("reference") String reference) throws Exception {
        return ResponseEntity.ok(payGeniusService.secureV1(request,headers,reference));
    }

    @PostMapping(path = "/secure-3D-v2", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChallengedResponse> secureV2(@Valid @RequestBody Request3DSecureV2 request, @RequestHeader Map<String, String> headers ) throws Exception {
        return ResponseEntity.ok(payGeniusService.secureV2(request,headers));
    }

    @PostMapping(path = "/secure-3D-v3", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NonChallengedResponse> secureV3(@Valid @RequestBody Request3DSecureV2 request, @RequestHeader Map<String, String> headers ) throws Exception {
        return ResponseEntity.ok(payGeniusService.secureV3(request,headers));
    }


   /* @PostMapping(path = "/partial-refund/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
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
    }*/
}
