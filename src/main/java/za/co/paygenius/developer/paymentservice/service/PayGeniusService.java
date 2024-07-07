package za.co.paygenius.developer.paymentservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import za.co.paygenius.developer.paymentservice.dto.CreatePaymentRequest;
import za.co.paygenius.developer.paymentservice.dto.CreatePaymentResponse;
import za.co.paygenius.developer.paymentservice.feign.PayGeniusFeignClient;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Map;

@Service
public class PayGeniusService {
    @Value("${paygenius.endpoint}")
    private String createPaymentUrl;

    @Autowired
    PayGeniusFeignClient payGeniusFeignClient;


    public String getSignature(final String payload, Map<String, String> headers) throws Exception {
        String secret = headers.get("x-secret");
        Mac hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(Charset.defaultCharset()), "HmacSHA256");
        hmac.init(secretKeySpec);

        return new String(Hex.encodeHex(hmac.doFinal(String.format("%s\n%s", createPaymentUrl, payload).trim().getBytes(Charset.defaultCharset()))));
    }

    public CreatePaymentResponse createPayment(final CreatePaymentRequest createPaymentRequest, Map<String, String> headers) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(createPaymentRequest);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        CreatePaymentResponse createPaymentResponse = payGeniusFeignClient.createPayment(createPaymentRequest, updatedHeaders);
        return createPaymentResponse;
    }

}
