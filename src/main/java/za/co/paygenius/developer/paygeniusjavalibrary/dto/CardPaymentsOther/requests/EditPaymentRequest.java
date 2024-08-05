package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.requests;

import feign.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.Consumer;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.request.AbstractRequest;

@Getter
@Setter
//@AllArgsConstructor
public class EditPaymentRequest extends AbstractRequest {
    private Consumer consumer;
    private String description;
    private String transactionDate;

    public EditPaymentRequest(String reference){super(Request.HttpMethod.POST, "/v3/payment/{reference}/edit");}

}