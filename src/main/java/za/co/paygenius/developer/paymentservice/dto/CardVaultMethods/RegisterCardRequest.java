package za.co.paygenius.developer.paymentservice.dto.CardVaultMethods;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.paygenius.developer.paymentservice.dto.CreditCard;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCardRequest {
    private CreditCard creditCard;
}
