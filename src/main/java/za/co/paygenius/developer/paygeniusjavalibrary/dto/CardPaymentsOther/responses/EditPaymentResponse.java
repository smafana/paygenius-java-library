package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditPaymentResponse {
    private boolean success;
    private String reference;
    private String message;
}
