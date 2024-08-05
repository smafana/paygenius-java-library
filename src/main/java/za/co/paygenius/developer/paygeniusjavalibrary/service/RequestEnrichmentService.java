package za.co.paygenius.developer.paygeniusjavalibrary.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.request.AbstractRequest;

import java.util.Map;

import static za.co.paygenius.developer.paygeniusjavalibrary.service.GenerateSignature.getSignature;

@Service
public class RequestEnrichmentService {

    @Value("${paygenius.url.baseUrl}")
    private String baseUrl;

    private static final Logger logger = LoggerFactory.getLogger(RequestEnrichmentService.class);

    public HttpHeaders prepareRequest(AbstractRequest request, Map<String, String> headers) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(request);
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(jsonPayload, baseUrl+request.getEndPoint(), headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        logger.info("calling :"+baseUrl+request.getEndPoint());
        return updatedHeaders;
    }

    public HttpHeaders prepareGetRequest(String url,Map<String, String> headers) throws Exception {
        HttpHeaders updatedHeaders = new HttpHeaders();
        updatedHeaders.setAll(headers);
        updatedHeaders.set("X-Signature", getSignature(null,url, headers));
        updatedHeaders.set("X-Token", headers.get("x-token"));
        updatedHeaders.remove("x-secret");
        updatedHeaders.remove("content-length");
        logger.info("Calling: "+url);
        return updatedHeaders;
    }
}
