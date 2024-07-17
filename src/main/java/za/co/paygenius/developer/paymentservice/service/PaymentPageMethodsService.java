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
import za.co.paygenius.developer.paymentservice.dto.PaymentPageMethods.CreateRedirectAffiliateMerchantRequest;
import za.co.paygenius.developer.paymentservice.dto.PaymentPageMethods.CreateRedirectRequest;
import za.co.paygenius.developer.paymentservice.dto.PaymentPageMethods.CreateRedirectResponse;
import za.co.paygenius.developer.paymentservice.dto.PaymentPageMethods.LookupPaymentResponse;
import za.co.paygenius.developer.paymentservice.dto.TransactionResponse;
import za.co.paygenius.developer.paymentservice.feign.CardPaymentOtherFeignClient;
import za.co.paygenius.developer.paymentservice.feign.PaymentPageMethodsFeignClient;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Map;

@Service
@Slf4j
public class PaymentPageMethodsService {
    private static final Logger log = LoggerFactory.getLogger(PaymentPageMethodsService.class);

    @Value("${paygenius.url.baseUrl}")
    private String baseUrl;

    @Autowired
    PaymentPageMethodsFeignClient paymentPageMethodsFeignClient;

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

    public CreateRedirectResponse createRedirect(final CreateRedirectRequest request, Map<String, String> headers) throws Exception{
        String url = baseUrl+"/v2/redirect/create";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(request);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("calling :"+url);
        CreateRedirectResponse response = paymentPageMethodsFeignClient.createRedirect(request, updatedHeaders);
        return response;
    }

    public CreateRedirectResponse createRedirectAffiliateMerchant(final CreateRedirectAffiliateMerchantRequest request, Map<String, String> headers) throws Exception{
        String url = baseUrl+"/v2/redirect/create";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(request);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("calling :"+url);
        CreateRedirectResponse response = paymentPageMethodsFeignClient.createRedirectAffiliateMerchant(request, updatedHeaders);
        return response;
    }

    public LookupPaymentResponse lookupPayment(Map<String,String> headers, String reference) throws Exception{
        String url = baseUrl+"/v2/redirect/"+reference;
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(null,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("calling: "+url);
        LookupPaymentResponse response = paymentPageMethodsFeignClient.LookupPayment(reference, updatedHeaders);
        return response;
    }
}
