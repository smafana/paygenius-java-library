package za.co.paygenius.developer.paymentservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import za.co.paygenius.developer.paymentservice.dto.*;

@FeignClient(name = "paygenius-client", url = "${paygenius.url.baseUrl}")

public interface PayGeniusFeignClient {

    @PostMapping("/v2/payment/create")
    CreatePaymentResponse createPayment(@RequestBody CreatePaymentRequest createPaymentRequest, @RequestHeader HttpHeaders headers);

    @PostMapping("/v2/payment/create/eft")
    CreateEFTPaymentResponse createPaymentEFT(@RequestBody CreateEFTPaymentRequest createEFTPaymentRequest, @RequestHeader HttpHeaders headers);

    @PostMapping("/v3/payment/{reference}/refund")
    RefundResponse refundPartialAmount(@RequestBody PartialRefundRequest partialRefundRequest, @PathVariable("reference") String reference, @RequestHeader HttpHeaders headers);

    @GetMapping("/v3/payment/{reference}/refund")
    RefundResponse refundFullAmount(@PathVariable("reference") String reference, @RequestHeader HttpHeaders headers);
}
