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
//@AllArgsConstructor
public class PartialRefundRequest extends AbstractRequest {
    private Transaction transaction;

    public PartialRefundRequest(String reference){
        super(Request.HttpMethod.POST, "/v2/payment/"+reference+"/refund");
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
