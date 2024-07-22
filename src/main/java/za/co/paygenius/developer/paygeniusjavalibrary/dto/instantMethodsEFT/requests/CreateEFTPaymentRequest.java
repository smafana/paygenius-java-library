package za.co.paygenius.developer.paygeniusjavalibrary.dto.instantMethodsEFT.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.Consumer;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.Transaction;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.Urls;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEFTPaymentRequest {
    private Transaction transaction;
    private Consumer consumer;
    private Urls urls;
}
