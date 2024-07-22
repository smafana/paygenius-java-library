package za.co.paygenius.developer.paygeniusjavalibrary.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.PaymentPageMethods.requests.CreateRedirectAffiliateMerchantRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.PaymentPageMethods.requests.CreateRedirectRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.PaymentPageMethods.reponses.CreateRedirectResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.PaymentPageMethods.reponses.LookupPaymentResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.service.PaymentPageMethodsService;

import java.util.Map;

@RestController
public class PaymentPageMethodsController {
    @Autowired
    private PaymentPageMethodsService paymentPageMethodsService;

    @PostMapping(path = "/create-redirect", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateRedirectResponse> createRedirect(@Valid @RequestBody CreateRedirectRequest request, @RequestHeader Map<String, String> headers) throws Exception {
        return ResponseEntity.ok(paymentPageMethodsService.createRedirect(request, headers));
    }

    @PostMapping(path = "/create-redirect-affiliate-merchant", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateRedirectResponse> createRedirectAffiliate(@Valid @RequestBody CreateRedirectAffiliateMerchantRequest request, @RequestHeader Map<String, String> headers) throws Exception {
        return ResponseEntity.ok(paymentPageMethodsService.createRedirectAffiliateMerchant(request, headers));
    }

    @GetMapping(path = "/redirect/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LookupPaymentResponse> lookupPayment(@RequestHeader Map<String,String> headers, @PathVariable("reference") String reference) throws Exception{
        return ResponseEntity.ok(paymentPageMethodsService.lookupPayment(headers,reference));
    }
}
