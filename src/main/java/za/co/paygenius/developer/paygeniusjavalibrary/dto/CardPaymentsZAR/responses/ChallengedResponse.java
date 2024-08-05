package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.responses;

import lombok.Getter;
import lombok.Setter;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.requests.ThreeDSecureV2;

@Getter
@Setter
public class ChallengedResponse {
    private boolean success;
    private String transactionDate;
    private String message;
    private ThreeDSecureV2 threeDSecureV2;
    private int code;
}
