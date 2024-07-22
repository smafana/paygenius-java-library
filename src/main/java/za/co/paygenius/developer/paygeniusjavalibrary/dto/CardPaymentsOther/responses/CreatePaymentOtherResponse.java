package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentOtherResponse {
    private boolean success;
    private String reference;
    private int code;
    private String redirectUrl;
    private String message;
}
