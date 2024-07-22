package za.co.paygenius.developer.paygeniusjavalibrary.dto.PaymentPageMethods.reponses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRedirectResponse {
    private boolean success;
    private String redirectUrl;
    private String reference;
}
