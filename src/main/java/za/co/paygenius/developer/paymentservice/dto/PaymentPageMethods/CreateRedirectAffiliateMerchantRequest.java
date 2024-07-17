package za.co.paygenius.developer.paymentservice.dto.PaymentPageMethods;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.paygenius.developer.paymentservice.dto.Consumer;
import za.co.paygenius.developer.paymentservice.dto.Transaction;
import za.co.paygenius.developer.paymentservice.dto.Urls;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRedirectAffiliateMerchantRequest {
    private Transaction transaction;
    private Consumer consumer;
    private boolean threeDSecure;
    private Urls urls;
    private boolean allowMultiCurrencyPurchase;
    private String description;
    private String transactionDate;
    private String destinationCountry;
    private int pax;
    private int affiliateMerchantId;
    private Transfer transfer;
}
