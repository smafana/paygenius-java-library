package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.requests;

import feign.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.request.AbstractRequest;

@Getter
@Setter
//@AllArgsConstructor
public class LookupCardTokenRequest extends AbstractRequest {
    public String token;

    public LookupCardTokenRequest(){
        super(Request.HttpMethod.POST, "v2/card/lookup");
    }
}
