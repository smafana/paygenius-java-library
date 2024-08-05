package za.co.paygenius.developer.paygeniusjavalibrary.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.PaymentPageMethods.requests.CreateRedirectAffiliateMerchantRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.PaymentPageMethods.requests.CreateRedirectRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.PaymentPageMethods.reponses.CreateRedirectResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.PaymentPageMethods.reponses.LookupPaymentResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.feign.PaymentPageMethodsFeignClient;
import java.util.Map;
import static za.co.paygenius.developer.paygeniusjavalibrary.service.GenerateSignature.getSignature;

@Service
@Slf4j
public class PaymentPageMethodsService {
    /*private static final Logger log = LoggerFactory.getLogger(PaymentPageMethodsService.class);

    @Value("${paygenius.url.baseUrl}")
    private String baseUrl;

    @Autowired
    PaymentPageMethodsFeignClient paymentPageMethodsFeignClient;


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
    }*/
}
