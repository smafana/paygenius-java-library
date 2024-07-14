package za.co.paygenius.developer.paymentservice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import za.co.paygenius.developer.paymentservice.dto.CardVaultMethods.*;
import za.co.paygenius.developer.paymentservice.service.PayGeniusService;

import java.util.Map;
@RestController
public class CardVaultController {
    @Autowired
    private PayGeniusService payGeniusService;

    @PostMapping(path = "/card/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterCardResponse> registerCard(@Valid @RequestBody RegisterCardRequest request, @RequestHeader Map<String,String> headers) throws Exception{
        return ResponseEntity.ok(payGeniusService.registerCard(request, headers));
    }

    @PostMapping(path = "/card/lookup-by-number", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LookupCardResponse> lookupCardNumber(@Valid@RequestBody LookupCardNumberRequest request, @RequestHeader Map<String,String> headers) throws Exception{
        return ResponseEntity.ok(payGeniusService.lookupCardNumber(request, headers));
    }

    @PostMapping(path = "/card/lookup-by-token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LookupCardResponse> lookupCardToken(@Valid@RequestBody LookupCardTokenRequest request, @RequestHeader Map<String,String> headers) throws Exception{
        return ResponseEntity.ok(payGeniusService.lookupCardToken(request, headers));
    }

    @PostMapping(path = "/card/lookup-by-transaction-reference", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LookupCardResponse> lookupCardTransactionReference(@Valid@RequestBody LookupCardTransactionReferenceRequest request, @RequestHeader Map<String,String> headers) throws Exception{
        return ResponseEntity.ok(payGeniusService.lookupCardTransactionReference(request, headers));
    }
}
