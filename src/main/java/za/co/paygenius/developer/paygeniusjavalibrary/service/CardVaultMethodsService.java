package za.co.paygenius.developer.paygeniusjavalibrary.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.requests.LookupCardNumberRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.requests.LookupCardTokenRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.requests.LookupCardTransactionReferenceRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.requests.RegisterCardRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.responses.LookupCardResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.responses.RegisterCardResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.responses.UnregisterCardResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.feign.CardVaultMethodsFeignClient;

import java.util.Map;

import static za.co.paygenius.developer.paygeniusjavalibrary.service.GenerateSignature.getSignature;

@Service
@Slf4j
public class CardVaultMethodsService {
    private static final Logger log = LoggerFactory.getLogger(CardVaultMethodsService.class);
    @Value("${paygenius.url.baseUrl}")
    private String baseUrl;

    @Autowired
    CardVaultMethodsFeignClient cardVaultMethodsFeignClient;

    public RegisterCardResponse registerCard(final RegisterCardRequest registerCardRequest, Map<String, String> headers) throws Exception {
        String url = baseUrl+"/v2/card/register";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(registerCardRequest);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload, url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("calling :"+url);
        RegisterCardResponse registerCardResponse = cardVaultMethodsFeignClient.registerCard(registerCardRequest, updatedHeaders);
        return registerCardResponse;
    }

    public LookupCardResponse lookupCardNumber(final LookupCardNumberRequest request, Map<String, String> headers) throws Exception {
        String url = baseUrl+"/v2/card/lookup";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(request);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("Calling: "+url);
        LookupCardResponse response = cardVaultMethodsFeignClient.lookupCardByNumber(request, updatedHeaders);
        return response;
    }

    public LookupCardResponse lookupCardToken(final LookupCardTokenRequest request, Map<String, String> headers) throws Exception {
        String url = baseUrl+"/v2/card/lookup";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(request);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("Calling: "+url);
        LookupCardResponse response = cardVaultMethodsFeignClient.lookupCardByToken(request, updatedHeaders);
        return response;
    }

    public LookupCardResponse lookupCardTransactionReference(final LookupCardTransactionReferenceRequest request, Map<String, String> headers) throws Exception {
        String url = baseUrl+"/v2/card/lookup";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(request);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("Calling: "+url);
        LookupCardResponse response = cardVaultMethodsFeignClient.lookupCardByTransactionReference(request, updatedHeaders);
        return response;
    }

    public UnregisterCardResponse unregisterCard(final LookupCardTokenRequest request, Map<String, String> headers) throws Exception {
        String url = baseUrl+"/v2/card/unregister";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(request);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload, url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("calling :"+url);
        UnregisterCardResponse response = cardVaultMethodsFeignClient.unregisterCard(request, updatedHeaders);
        return response;
    }

}
