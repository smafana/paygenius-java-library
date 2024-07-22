package za.co.paygenius.developer.paygeniusjavalibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {

    private String number;
    private String cardHolder;
    private String expiryYear;
    private String expiryMonth;
    private String type;
    private String cvv;

}
