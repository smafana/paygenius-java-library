package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.requests;

import feign.Request;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.request.AbstractRequest;

public class Request3DSecureV1 extends AbstractRequest {
    private String paRes;

    public Request3DSecureV1(Request.HttpMethod method, String endPoint) {
        super(method, endPoint);
    }

    public String getPaRes() {
        return paRes;
    }

    public void setPaRes(String paRes) {
        this.paRes = paRes;
    }
}
