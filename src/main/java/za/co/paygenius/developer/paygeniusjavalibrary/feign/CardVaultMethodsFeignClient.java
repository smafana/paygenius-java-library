package za.co.paygenius.developer.paygeniusjavalibrary.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.requests.LookupCardNumberRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.requests.LookupCardTokenRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.requests.LookupCardTransactionReferenceRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.requests.RegisterCardRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.responses.LookupCardResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.responses.RegisterCardResponse;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.responses.UnregisterCardResponse;

@FeignClient(name = "cardVaultMethods-client", url = "${paygenius.url.baseUrl}")
public interface CardVaultMethodsFeignClient {
    @PostMapping("/v2/card/register")
    RegisterCardResponse registerCard(@RequestBody RegisterCardRequest registerCardRequest, @RequestHeader HttpHeaders headers);

    @PostMapping("v2/card/lookup")
    LookupCardResponse lookupCardByNumber(@RequestBody LookupCardNumberRequest Request, @RequestHeader HttpHeaders headers);

    @PostMapping("v2/card/lookup")
    LookupCardResponse lookupCardByToken(@RequestBody LookupCardTokenRequest Request, @RequestHeader HttpHeaders headers);

    @PostMapping("v2/card/lookup")
    LookupCardResponse lookupCardByTransactionReference(@RequestBody LookupCardTransactionReferenceRequest Request, @RequestHeader HttpHeaders headers);

    @PostMapping("/v2/card/unregister")
    UnregisterCardResponse unregisterCard(@RequestBody LookupCardTokenRequest request, @RequestHeader HttpHeaders headers);
}
