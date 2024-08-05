package za.co.paygenius.developer.paygeniusjavalibrary.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import za.co.paygenius.developer.paygeniusjavalibrary.feign.CardPaymentRandFeignClient;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Map;

@Service
@Slf4j
public class GenerateSignature {
    private static final Logger log = LoggerFactory.getLogger(GenerateSignature.class);
    @Value("${paygenius.url.baseUrl}/v2/payment/create")
    private String createPaymentUrl;
    @Value("${paygenius.url.baseUrl}/v2/payment/create/eft")
    private String createEftPaymentUrl;
    @Value("${paygenius.url.baseUrl}")
    private String baseUrl;

    @Autowired
    CardPaymentRandFeignClient cardPaymentRandFeignClient;

    public static String getSignature(final String payload, String url, Map<String, String> headers) throws Exception {
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
}
