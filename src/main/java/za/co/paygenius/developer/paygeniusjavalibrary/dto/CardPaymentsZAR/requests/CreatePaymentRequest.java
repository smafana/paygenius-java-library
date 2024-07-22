package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CreditCard;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.Transaction;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {

    private CreditCard creditCard;
    private Transaction transaction;
    private boolean threeDSecure;
    private boolean supports3dsv2;
    private boolean autoExecute;
    private String callbackUrl;

}
