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
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsOther.CreatePaymentOtherRequest;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsOther.CreatePaymentOtherResponse;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsOther.ExecutePaymentOtherRequest;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsOther.RefundPaymentOtherRequest;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsZAR.PartialRefundRequest;
import za.co.paygenius.developer.paymentservice.dto.TransactionResponse;
import za.co.paygenius.developer.paymentservice.feign.CardPaymentOtherFeignClient;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Map;

@Service
@Slf4j
public class CreatePaymentOtherService {
    private static final Logger log = LoggerFactory.getLogger(CreatePaymentOtherService.class);

    @Value("${paygenius.url.baseUrl}")
    private String baseUrl;

    @Autowired
    CardPaymentOtherFeignClient cardPaymentOtherFeignClient;

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
}
