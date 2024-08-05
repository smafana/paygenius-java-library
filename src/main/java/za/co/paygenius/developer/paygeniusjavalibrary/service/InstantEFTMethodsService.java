package za.co.paygenius.developer.paygeniusjavalibrary.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.instantMethodsEFT.requests.CreateEFTPaymentRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.instantMethodsEFT.responses.CreateEFTPaymentResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.feign.InstantEFTMethodsFeignClient;

import java.util.Map;

import static za.co.paygenius.developer.paygeniusjavalibrary.service.GenerateSignature.getSignature;

@Service
@Slf4j
public class InstantEFTMethodsService {
    /*private static final Logger log = LoggerFactory.getLogger(InstantEFTMethodsService.class);
    @Value("${paygenius.url.baseUrl}")
    private String baseUrl;

    @Autowired
    InstantEFTMethodsFeignClient instantEFTMethodsFeignClient;


    public CreateEFTPaymentResponse createPaymentEFT(final CreateEFTPaymentRequest createEFTPaymentRequest, Map<String,String> headers) throws Exception{
        String url = baseUrl+"/v2/payment/create/eft";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(createEFTPaymentRequest);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        log.info("calling :"+url);
        CreateEFTPaymentResponse createEFTPaymentResponse = instantEFTMethodsFeignClient.createPaymentEFT(createEFTPaymentRequest, updatedHeaders);
        return createEFTPaymentResponse;
    }*/
}
