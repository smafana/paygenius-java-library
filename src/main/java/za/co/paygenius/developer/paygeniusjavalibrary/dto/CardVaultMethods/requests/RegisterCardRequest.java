package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardVaultMethods.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CreditCard;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCardRequest {
    private CreditCard creditCard;
}
