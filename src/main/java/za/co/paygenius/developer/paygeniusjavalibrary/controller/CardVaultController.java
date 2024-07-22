package za.co.paygenius.developer.paygeniusjavalibrary.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.requests.LookupCardNumberRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.requests.LookupCardTokenRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.requests.LookupCardTransactionReferenceRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.requests.RegisterCardRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.responses.LookupCardResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.responses.RegisterCardResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.responses.UnregisterCardResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.service.CardVaultMethodsService;

import java.util.Map;
@RestController
public class CardVaultController {
    @Autowired
    private CardVaultMethodsService cardVaultMethodsService;

    @PostMapping(path = "/card/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterCardResponse> registerCard(@Valid @RequestBody RegisterCardRequest request, @RequestHeader Map<String,String> headers) throws Exception{
        return ResponseEntity.ok(cardVaultMethodsService.registerCard(request, headers));
    }

    @PostMapping(path = "/card/lookup-by-number", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LookupCardResponse> lookupCardNumber(@Valid@RequestBody LookupCardNumberRequest request, @RequestHeader Map<String,String> headers) throws Exception{
        return ResponseEntity.ok(cardVaultMethodsService.lookupCardNumber(request, headers));
    }

    @PostMapping(path = "/card/lookup-by-token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LookupCardResponse> lookupCardToken(@Valid@RequestBody LookupCardTokenRequest request, @RequestHeader Map<String,String> headers) throws Exception{
        return ResponseEntity.ok(cardVaultMethodsService.lookupCardToken(request, headers));
    }

    @PostMapping(path = "/card/lookup-by-transaction-reference", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LookupCardResponse> lookupCardTransactionReference(@Valid@RequestBody LookupCardTransactionReferenceRequest request, @RequestHeader Map<String,String> headers) throws Exception{
        return ResponseEntity.ok(cardVaultMethodsService.lookupCardTransactionReference(request, headers));
    }

    @PostMapping(path = "/card/unregister", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UnregisterCardResponse> unregisterCard(@Valid@RequestBody LookupCardTokenRequest request, @RequestHeader Map<String,String> headers) throws Exception{
        return ResponseEntity.ok(cardVaultMethodsService.unregisterCard(request, headers));
    }
}
