package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.requests;

import feign.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.Transaction;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.request.AbstractRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExecutePaymentRequest extends AbstractRequest {
    public Transaction transaction;

    public ExecutePaymentRequest(String reference) {
        super(Request.HttpMethod.POST, "/v2/payment/"+reference+"/execute");
    }
}
