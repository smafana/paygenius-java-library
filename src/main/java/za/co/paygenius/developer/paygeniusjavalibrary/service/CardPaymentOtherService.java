package za.co.paygenius.developer.paygeniusjavalibrary.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.requests.*;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.responses.CreatePaymentOtherResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.responses.EditPaymentResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.TransactionResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.feign.CardPaymentOtherFeignClient;

import java.util.Map;

import static za.co.paygenius.developer.paygeniusjavalibrary.service.GenerateSignature.getSignature;

@Service
@Slf4j
public class CardPaymentOtherService {
    private static final Logger log = LoggerFactory.getLogger(CardPaymentOtherService.class);

    @Value("${paygenius.url.baseUrl}")
    private String baseUrl;

    @Autowired
    CardPaymentOtherFeignClient cardPaymentOtherFeignClient;


    public CreatePaymentOtherResponse createPaymentWithAuthorize(final CreatePaymentOtherRequest request, Map<String, String> headers) throws Exception{
        String url = baseUrl+"/v3/payment/create";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(request);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("calling :"+url);
        CreatePaymentOtherResponse response = cardPaymentOtherFeignClient.createPaymentWithAuthorize(request, updatedHeaders);
        return response;
    }

    public TransactionResponse partialExecutePaymentOther(final ExecutePaymentOtherRequest request, Map<String,String> headers, String reference) throws Exception{
        String url = baseUrl+"/v3/payment/"+reference+"/execute";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(request);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("calling :"+url);
        TransactionResponse transactionResponse = cardPaymentOtherFeignClient.partialExecutePayment(request, updatedHeaders, reference);
        return transactionResponse;
    }

    public TransactionResponse executePaymentOther(Map<String,String> headers, String reference) throws Exception{
        String url = baseUrl+"/v3/payment/"+reference+"/execute";
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(null,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("Calling: "+url);
        TransactionResponse transactionResponse = cardPaymentOtherFeignClient.executePayment(reference, updatedHeaders);
        return transactionResponse;
    }

    public TransactionResponse partialRefundPaymentOther(final RefundPaymentOtherRequest request, Map<String,String> headers, String reference) throws Exception{
        String url = baseUrl+"/v3/payment/"+reference+"/refund";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(request);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("calling :"+url);
        TransactionResponse transactionResponse = cardPaymentOtherFeignClient.partialRefundPayment(request, updatedHeaders, reference);
        return transactionResponse;
    }

    public TransactionResponse fullRefundPaymentOther(Map<String,String> headers, String reference) throws Exception{
        String url = baseUrl+"/v3/payment/"+reference+"/refund";
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(null,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("Calling: "+url);
        TransactionResponse transactionResponse = cardPaymentOtherFeignClient.fullRefundPayment(reference, updatedHeaders);
        return transactionResponse;
    }

    public EditPaymentResponse editPayment(final EditPaymentRequest request, Map<String,String> headers, String reference) throws Exception{
        String url = baseUrl+"/v3/payment/"+reference+"/edit";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(request);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("calling :"+url);
        EditPaymentResponse editResponse = cardPaymentOtherFeignClient.editPayment(request, updatedHeaders, reference);
        return editResponse;
    }

    public EditPaymentResponse editPaymentAdvanced(final EditPaymentAdvancedRequest request, Map<String,String> headers, String reference) throws Exception{
        String url = baseUrl+"/v3/payment/"+reference+"/edit";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(request);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("calling :"+url);
        EditPaymentResponse editResponse = cardPaymentOtherFeignClient.editPaymentAdvanced(request, updatedHeaders, reference);
        return editResponse;
    }
}
