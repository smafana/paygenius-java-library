package za.co.paygenius.developer.paygeniusjavalibrary.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.PaymentPageMethods.requests.CreateRedirectAffiliateMerchantRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.PaymentPageMethods.requests.CreateRedirectRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.PaymentPageMethods.reponses.CreateRedirectResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.PaymentPageMethods.reponses.LookupPaymentResponse;

@FeignClient(name = "PaymentPage-client", url = "${paygenius.url.baseUrl}")
public interface PaymentPageMethodsFeignClient {
    @PostMapping("/v2/redirect/create")
    CreateRedirectResponse createRedirect(@RequestBody CreateRedirectRequest request, @RequestHeader HttpHeaders headers);

    @PostMapping("/v2/redirect/create")
    CreateRedirectResponse createRedirectAffiliateMerchant(@RequestBody CreateRedirectAffiliateMerchantRequest request, @RequestHeader HttpHeaders headers);

    @GetMapping("/v2/redirect/{payment-reference}")
    LookupPaymentResponse LookupPayment(@PathVariable("reference") String reference, @RequestHeader HttpHeaders headers);

}
