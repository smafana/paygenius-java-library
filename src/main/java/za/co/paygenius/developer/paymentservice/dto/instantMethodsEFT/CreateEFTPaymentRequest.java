package za.co.paygenius.developer.paymentservice.dto.instantMethodsEFT;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.paygenius.developer.paymentservice.dto.Consumer;
import za.co.paygenius.developer.paymentservice.dto.Transaction;
import za.co.paygenius.developer.paymentservice.dto.Urls;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEFTPaymentRequest {
    private Transaction transaction;
    private Consumer consumer;
    private Urls urls;
}
