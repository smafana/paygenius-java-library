package za.co.paygenius.developer.paymentservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import za.co.paygenius.developer.paymentservice.dto.*;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsZAR.CreatePaymentRequest;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsZAR.CreatePaymentResponse;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsZAR.ExecutePaymentRequest;
import za.co.paygenius.developer.paymentservice.dto.CardPaymentsZAR.PartialRefundRequest;
import za.co.paygenius.developer.paymentservice.dto.CardVaultMethods.*;
import za.co.paygenius.developer.paymentservice.dto.instantMethodsEFT.CreateEFTPaymentRequest;
import za.co.paygenius.developer.paymentservice.dto.instantMethodsEFT.CreateEFTPaymentResponse;

@FeignClient(name = "paygenius-client", url = "${paygenius.url.baseUrl}")

public interface PayGeniusFeignClient {

    @PostMapping("/v2/payment/create")
    CreatePaymentResponse createPayment(@RequestBody CreatePaymentRequest createPaymentRequest, @RequestHeader HttpHeaders headers);

    @PostMapping("/v2/payment/create/eft")
    CreateEFTPaymentResponse createPaymentEFT(@RequestBody CreateEFTPaymentRequest createEFTPaymentRequest, @RequestHeader HttpHeaders headers);

    @PostMapping("/v3/payment/{reference}/refund")
    TransactionResponse refundPartialAmount(@RequestBody PartialRefundRequest partialRefundRequest, @PathVariable("reference") String reference, @RequestHeader HttpHeaders headers);

    @GetMapping("/v3/payment/{reference}/refund")
    TransactionResponse refundFullAmount(@PathVariable("reference") String reference, @RequestHeader HttpHeaders headers);

    @GetMapping("/v2/payment/{reference}/execute")
    TransactionResponse executeTransaction(@PathVariable("reference") String reference, @RequestHeader HttpHeaders headers);

    @PostMapping("/v2/payment/{reference}/execute")
    TransactionResponse executePartTransaction(@RequestBody ExecutePaymentRequest executePaymentRequest, @RequestHeader HttpHeaders headers, @PathVariable("reference") String reference);

    @PostMapping("/v2/card/register")
    RegisterCardResponse registerCard(@RequestBody RegisterCardRequest registerCardRequest, @RequestHeader HttpHeaders headers);

    @PostMapping("v2/card/lookup")
    LookupCardResponse lookupCardByNumber(@RequestBody LookupCardNumberRequest Request, @RequestHeader HttpHeaders headers);

    @PostMapping("v2/card/lookup")
    LookupCardResponse lookupCardByToken(@RequestBody LookupCardTokenRequest Request, @RequestHeader HttpHeaders headers);

    @PostMapping("v2/card/lookup")
    LookupCardResponse lookupCardByTransactionReference(@RequestBody LookupCardTransactionReferenceRequest Request, @RequestHeader HttpHeaders headers);
}
