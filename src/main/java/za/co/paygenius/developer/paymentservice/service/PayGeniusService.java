package za.co.paygenius.developer.paymentservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import za.co.paygenius.developer.paymentservice.dto.*;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsZAR.CreatePaymentRequest;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsZAR.CreatePaymentResponse;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsZAR.ExecutePaymentRequest;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsZAR.PartialRefundRequest;
import za.co.paygenius.developer.paymentservice.dto.CardVaultMethods.*;
import za.co.paygenius.developer.paymentservice.dto.instantMethodsEFT.CreateEFTPaymentRequest;
import za.co.paygenius.developer.paymentservice.dto.instantMethodsEFT.CreateEFTPaymentResponse;
import za.co.paygenius.developer.paymentservice.feign.PayGeniusFeignClient;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Map;

@Service
@Slf4j
public class PayGeniusService {
    private static final Logger log = LoggerFactory.getLogger(PayGeniusService.class);
    @Value("${paygenius.url.baseUrl}/v2/payment/create")
    private String createPaymentUrl;
    @Value("${paygenius.url.baseUrl}/v2/payment/create/eft")
    private String createEftPaymentUrl;
    @Value("${paygenius.url.baseUrl}")
    private String baseUrl;



    @Autowired
    PayGeniusFeignClient payGeniusFeignClient;


    public String getSignature(final String payload, String url, Map<String, String> headers) throws Exception {
        String secret = headers.get("x-secret");
        Mac hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(Charset.defaultCharset()), "HmacSHA256");
        hmac.init(secretKeySpec);

        if(payload!=null) {
            return new String(Hex.encodeHex(hmac.doFinal(String.format("%s\n%s", url, payload).trim().getBytes(Charset.defaultCharset()))));
        }
        else {
            return new String(Hex.encodeHex(hmac.doFinal(url.trim().getBytes(Charset.defaultCharset()))));
        }
    }

    public CreatePaymentResponse createPayment(final CreatePaymentRequest createPaymentRequest, Map<String, String> headers) throws Exception {
        String url = baseUrl+"/v2/payment/create";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(createPaymentRequest);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        CreatePaymentResponse createPaymentResponse = payGeniusFeignClient.createPayment(createPaymentRequest, updatedHeaders);
        return createPaymentResponse;
    }

    public CreateEFTPaymentResponse createPaymentEFT(final CreateEFTPaymentRequest createEFTPaymentRequest, Map<String,String> headers) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(createEFTPaymentRequest);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload,createEftPaymentUrl, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        CreateEFTPaymentResponse createEFTPaymentResponse = payGeniusFeignClient.createPaymentEFT(createEFTPaymentRequest, updatedHeaders);
        return createEFTPaymentResponse;
    }

    public TransactionResponse partialRefund(final PartialRefundRequest partialRefundRequest, Map<String,String> headers, String reference) throws Exception{
        String url = baseUrl+"/v2/payment/"+reference+"/refund";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(partialRefundRequest);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("calling :"+url);
        TransactionResponse transactionResponse = payGeniusFeignClient.refundPartialAmount(partialRefundRequest, reference, updatedHeaders);
        return transactionResponse;
    }
    public TransactionResponse fullRefund(Map<String,String> headers, String reference) throws Exception{
        String url = baseUrl+"/v3/payment/"+reference+"/refund";
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(null,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("calling: "+url);
        TransactionResponse transactionResponse = payGeniusFeignClient.refundFullAmount(reference, updatedHeaders);
        return transactionResponse;
    }

    public TransactionResponse executeTransaction(Map<String,String> headers, String reference) throws Exception{
        String url = baseUrl+"/v2/payment/"+reference+"/execute";
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(null,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("Calling: "+url);
        TransactionResponse transactionResponse = payGeniusFeignClient.executeTransaction(reference, updatedHeaders);
        return transactionResponse;
    }

    public TransactionResponse executePartTransaction(final ExecutePaymentRequest executePaymentRequest, Map<String,String> headers, String reference) throws Exception{
        String url = baseUrl+"/v2/payment/"+reference+"/execute";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(executePaymentRequest);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("Calling: "+url);
        TransactionResponse transactionResponse = payGeniusFeignClient.executePartTransaction(executePaymentRequest, updatedHeaders, reference);
        return transactionResponse;
    }

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
        log.info("Calling: "+url);
        RegisterCardResponse registerCardResponse = payGeniusFeignClient.registerCard(registerCardRequest, updatedHeaders);
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
        LookupCardResponse response = payGeniusFeignClient.lookupCardByNumber(request, updatedHeaders);
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
        LookupCardResponse response = payGeniusFeignClient.lookupCardByToken(request, updatedHeaders);
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
        LookupCardResponse response = payGeniusFeignClient.lookupCardByTransactionReference(request, updatedHeaders);
        return response;
    }


}
