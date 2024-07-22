package za.co.paygenius.developer.paygeniusjavalibrary.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.requests.*;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.responses.CreatePaymentOtherResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.responses.EditPaymentResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.TransactionResponse;

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

    @GetMapping("/v3/payment/{reference}/refund")
    TransactionResponse fullRefundPayment(@PathVariable("reference") String reference, @RequestHeader HttpHeaders headers);

    @PostMapping("/v3/payment/{reference}/edit")
    EditPaymentResponse editPayment(@RequestBody EditPaymentRequest request, @RequestHeader HttpHeaders headers, @PathVariable("reference") String reference);

    @PostMapping("/v3/payment/{reference}/edit")
    EditPaymentResponse editPaymentAdvanced(@RequestBody EditPaymentAdvancedRequest request, @RequestHeader HttpHeaders headers, @PathVariable("reference") String reference);


}
