package za.co.paygenius.developer.paygeniusjavalibrary.dto.instantMethodsEFT.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEFTPaymentResponse {
    private boolean success;
    private String reference;
    private String redirectUrl;
}
