package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreeDSecureV2 {
    private Object tdsMethodContent;
    private Object txId;
    private String transactionId;
    private String sessionData;
    private String acsUrl;
    private String creq;
}
