package za.co.paygenius.developer.paygeniusjavalibrary.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.instantMethodsEFT.requests.CreateEFTPaymentRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.instantMethodsEFT.responses.CreateEFTPaymentResponse;

@FeignClient(name = "InstantEFT-client", url = "${paygenius.url.baseUrl}")
public interface InstantEFTMethodsFeignClient {
    @PostMapping("/v2/payment/create/eft")
    CreateEFTPaymentResponse createPaymentEFT(@RequestBody CreateEFTPaymentRequest createEFTPaymentRequest, @RequestHeader HttpHeaders headers);
}
