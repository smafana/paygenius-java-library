package za.co.paygenius.developer.paymentservice.dto.CardPaymentsOther;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private boolean success;
    private String reference;
    private int code;
    private String redirectUrl;
    private String message;
}
