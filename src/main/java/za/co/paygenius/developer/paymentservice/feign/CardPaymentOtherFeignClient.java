package za.co.paygenius.developer.paymentservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsOther.CreatePaymentOtherRequest;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsOther.CreatePaymentOtherResponse;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsOther.ExecutePaymentOtherRequest;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsOther.RefundPaymentOtherRequest;
import za.co.paygenius.developer.paymentservice.dto.TransactionResponse;

@FeignClient(name = "CardPaymentOther-client", url = "${paygenius.url.baseUrl}")
public interface CardPaymentOtherFeignClient {
    @PostMapping("/v3/payment/create")
    CreatePaymentOtherResponse createPaymentWithAuthorize(@RequestBody CreatePaymentOtherRequest request, @RequestHeader HttpHeaders headers);

    @GetMapping("/v3/payment/{reference}/execute")
    TransactionResponse executePayment(@PathVariable("reference") String reference, @RequestHeader HttpHeaders headers);

    @PostMapping("/v3/payment/{reference}/execute")
    TransactionResponse partialExecutePayment(@RequestBody ExecutePaymentOtherRequest request, @RequestHeader HttpHeaders headers, @PathVariable("reference") String reference);

    @PostMapping("/v3/payment/{reference}/refund")
    TransactionResponse partialRefundPayment(@RequestBody RefundPaymentOtherRequest request, @RequestHeader HttpHeaders headers, @PathVariable("reference") String reference);

    @GetMapping("/v3/payment/reference/refund")
    TransactionResponse fullRefundPayment(@PathVariable("reference") String reference, @RequestHeader HttpHeaders headers);
}
