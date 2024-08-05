package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.requests;

import feign.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CreditCard;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.Transaction;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.request.AbstractRequest;

@Getter
@Setter
//@AllArgsConstructor
public class CreatePaymentRequest extends AbstractRequest {

    private CreditCard creditCard;
    private Transaction transaction;
    private boolean threeDSecure;
    private boolean supports3dsv2;
    private boolean autoExecute;
    private String callbackUrl;

    public CreatePaymentRequest() {
        super(Request.HttpMethod.POST, "/v2/payment/create");
    }

}
