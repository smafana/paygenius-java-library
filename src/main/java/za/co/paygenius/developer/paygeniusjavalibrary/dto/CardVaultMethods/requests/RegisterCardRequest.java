package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.requests;

import feign.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CreditCard;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.request.AbstractRequest;

@Getter
@Setter
public class RegisterCardRequest extends AbstractRequest {
    private CreditCard creditCard;

    public RegisterCardRequest(){
        super(Request.HttpMethod.POST, "v2/card/unregister");
    }
}
