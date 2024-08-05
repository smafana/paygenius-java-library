package za.co.paygenius.developer.paygeniusjavalibrary.feign;

import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.requests.*;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.responses.ChallengedResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.responses.CreatePaymentResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.responses.NonChallengedResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.responses.Response3DSecureV1;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.TransactionResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.instantMethodsEFT.requests.CreateEFTPaymentRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.instantMethodsEFT.responses.CreateEFTPaymentResponse;

@FeignClient(name = "CardPaymentRand-client", url = "${paygenius.url.baseUrl}")
public interface CardPaymentRandFeignClient {
    @PostMapping("/v2/payment/create")
    CreatePaymentResponse createPayment(@RequestBody CreatePaymentRequest createPaymentRequest, @RequestHeader HttpHeaders headers);

    @PostMapping("/v2/payment/create/eft")
    CreateEFTPaymentResponse    createPaymentEFT(@RequestBody CreateEFTPaymentRequest createEFTPaymentRequest, @RequestHeader HttpHeaders headers);

    @PostMapping("/v2/payment/{reference}/refund")
    TransactionResponse refundPartialAmount(@RequestBody PartialRefundRequest partialRefundRequest, @PathVariable("reference") String reference, @RequestHeader HttpHeaders headers);

    @GetMapping("/v2/payment/{reference}/refund")
    TransactionResponse refundFullAmount(@PathVariable("reference") String reference, @RequestHeader HttpHeaders headers);

    @GetMapping("/v2/payment/{reference}/execute")
    TransactionResponse executeTransaction(@PathVariable("reference") String reference, @RequestHeader HttpHeaders headers);

    @PostMapping("/v2/payment/{reference}/execute")
    TransactionResponse executePartTransaction(@RequestBody ExecutePaymentRequest executePaymentRequest,@PathVariable("reference") String reference, @RequestHeader HttpHeaders headers);

    @GetMapping("/v2/payment/{reference}/refund")
    TransactionResponse fullRefund(@PathVariable("reference") String reference, @RequestHeader HttpHeaders headers);

    @PostMapping("/v2/payment/{reference}/confirm")
    Response3DSecureV1 secureV1(@RequestBody Request3DSecureV1 request, @PathVariable("reference") String reference, @RequestHeader HttpHeaders headers);

    @PostMapping("/v2/payment/3dsv2/{reference}/confirm")
    ChallengedResponse secureV2(@RequestBody Request3DSecureV2 request, @PathVariable("reference") String reference, @RequestHeader HttpHeaders headers);

    @PostMapping("/v2/payment/3dsv2/{reference}/confirm")
    NonChallengedResponse secureV3(@RequestBody Request3DSecureV2 request, @PathVariable("reference") String reference, @RequestHeader HttpHeaders headers);

}
