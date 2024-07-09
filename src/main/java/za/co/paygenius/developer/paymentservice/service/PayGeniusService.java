package za.co.paygenius.developer.paymentservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import za.co.paygenius.developer.paymentservice.dto.*;
import za.co.paygenius.developer.paymentservice.feign.PayGeniusFeignClient;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Map;

@Service
public class PayGeniusService {
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
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(createPaymentRequest);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload,createPaymentUrl, headers));
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

    public RefundResponse partialRefund(final PartialRefundRequest partialRefundRequest, Map<String,String> headers, String reference) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(partialRefundRequest);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload,baseUrl+"/v3/payment/"+reference+"/refund", headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        RefundResponse refundResponse = payGeniusFeignClient.refundPartialAmount(partialRefundRequest, reference, updatedHeaders);
        return refundResponse;
    }
    public RefundResponse fullRefund(Map<String,String> headers, String reference) throws Exception{
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(null,baseUrl+"/v3/payment/"+reference+"/refund", headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        RefundResponse refundResponse = payGeniusFeignClient.refundFullAmount(reference, updatedHeaders);
        return refundResponse;
    }

}
