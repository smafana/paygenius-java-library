package za.co.paygenius.developer.paygeniusjavalibrary.dto.request;

import feign.Request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractRequest {
    private Request.HttpMethod method = Request.HttpMethod.POST;
    private String endPoint = "";

    public AbstractRequest(Request.HttpMethod method, String endPoint) {
        this.method = method;
        this.endPoint = endPoint;
    }

    public Request.HttpMethod getMethod() {
        return method;
    }

    public void setMethod(Request.HttpMethod method) {
        this.method = method;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }
}
