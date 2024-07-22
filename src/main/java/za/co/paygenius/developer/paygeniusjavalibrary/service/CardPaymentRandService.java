package za.co.paygenius.developer.paygeniusjavalibrary.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.requests.CreatePaymentRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.requests.ExecutePaymentRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.requests.PartialRefundRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.responses.CreatePaymentResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.TransactionResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.feign.CardPaymentRandFeignClient;

import java.util.Map;

import static za.co.paygenius.developer.paygeniusjavalibrary.service.GenerateSignature.getSignature;

@Service
@Slf4j
public class CardPaymentRandService {
    private static final Logger log = LoggerFactory.getLogger(CardPaymentRandService.class);

    @Value("${paygenius.url.baseUrl}")
    private String baseUrl;

    @Autowired
    CardPaymentRandFeignClient cardPaymentRandFeignClient;


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
        log.info("calling :"+url);
        CreatePaymentResponse createPaymentResponse = cardPaymentRandFeignClient.createPayment(createPaymentRequest, updatedHeaders);
        return createPaymentResponse;
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
        TransactionResponse transactionResponse = cardPaymentRandFeignClient.refundPartialAmount(partialRefundRequest, reference, updatedHeaders);
        return transactionResponse;
    }

    public TransactionResponse fullRefund(Map<String,String> headers, String reference) throws Exception{
        String url = baseUrl+"/v2/payment/"+reference+"/refund";
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(null,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("calling: "+url);
        TransactionResponse transactionResponse = cardPaymentRandFeignClient.refundFullAmount(reference, updatedHeaders);
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
        TransactionResponse transactionResponse = cardPaymentRandFeignClient.executeTransaction(reference, updatedHeaders);
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
        TransactionResponse transactionResponse = cardPaymentRandFeignClient.executePartTransaction(executePaymentRequest, updatedHeaders, reference);
        return transactionResponse;
    }
}
