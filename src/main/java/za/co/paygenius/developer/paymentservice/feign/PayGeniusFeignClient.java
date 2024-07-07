package za.co.paygenius.developer.paymentservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import za.co.paygenius.developer.paymentservice.dto.CreatePaymentRequest;
import za.co.paygenius.developer.paymentservice.dto.CreatePaymentResponse;

@FeignClient(name = "paygenius-client", url = "${paygenius.feign-url}")

public interface PayGeniusFeignClient {

    @PostMapping("/create")
    CreatePaymentResponse createPayment(@RequestBody CreatePaymentRequest createPaymentRequest, @RequestHeader HttpHeaders headers);

}
