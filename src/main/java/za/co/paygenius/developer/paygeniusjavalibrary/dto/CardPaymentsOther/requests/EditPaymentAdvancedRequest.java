package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.Consumer;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.Transfer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditPaymentAdvancedRequest {
    private Consumer consumer;
    private String description;
    private String transactionDate;
    private String destinationCountry;
    private int pax;
    private Transfer transfer;
}
