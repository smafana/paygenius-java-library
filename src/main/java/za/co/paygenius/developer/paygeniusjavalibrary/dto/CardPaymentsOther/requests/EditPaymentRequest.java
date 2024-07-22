package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.Consumer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditPaymentRequest {
    private Consumer consumer;
    private String description;
    private String transactionDate;
}
