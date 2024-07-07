package za.co.paygenius.developer.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
