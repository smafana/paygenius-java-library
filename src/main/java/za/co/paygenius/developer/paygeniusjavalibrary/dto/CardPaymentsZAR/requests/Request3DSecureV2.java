package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.requests;

import feign.Request;
import lombok.Getter;
import lombok.Setter;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.request.AbstractRequest;

@Getter
@Setter
public class Request3DSecureV2 extends AbstractRequest {
    private String reference;
    private String txId;
    private String transactionId;

    public Request3DSecureV2(Request.HttpMethod method, String endPoint) {
        super(method, endPoint);
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
