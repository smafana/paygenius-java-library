package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.requests;

import feign.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.Consumer;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.Transfer;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.request.AbstractRequest;

@Getter
@Setter
public class EditPaymentAdvancedRequest extends AbstractRequest {
    private Consumer consumer;
    private String description;
    private String transactionDate;
    private String destinationCountry;
    private int pax;
    private Transfer transfer;

    public EditPaymentAdvancedRequest(String reference){super(Request.HttpMethod.POST, "/v3/payment/{reference}/edit");}

}
