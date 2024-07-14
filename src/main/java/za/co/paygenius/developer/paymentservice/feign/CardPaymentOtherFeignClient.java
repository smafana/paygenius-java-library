package za.co.paygenius.developer.paymentservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsOther.CreatePaymentOtherRequest;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsOther.Response;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsZAR.CreatePaymentRequest;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsZAR.CreatePaymentResponse;

@FeignClient(name = "CardPaymentOther-client", url = "${paygenius.url.baseUrl}")
public interface CardPaymentOtherFeignClient {
    @PostMapping("/v3/payment/create")
    Response createPaymentWithAuthorize(@RequestBody CreatePaymentOtherRequest request, @RequestHeader HttpHeaders headers);
}
