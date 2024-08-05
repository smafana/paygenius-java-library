package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.requests;

import feign.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.requests.ExecutePaymentRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.Transaction;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.request.AbstractRequest;

@Getter
@Setter
public class ExecutePaymentOtherRequest extends AbstractRequest {
    private Transaction transaction;
    ExecutePaymentOtherRequest(String reference){
        super(Request.HttpMethod.POST, "/v3/payment/{reference}/edit");
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
