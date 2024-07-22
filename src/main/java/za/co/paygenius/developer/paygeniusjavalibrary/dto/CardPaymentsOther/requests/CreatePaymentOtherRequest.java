package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.requests;

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
public class CreatePaymentOtherRequest {
    private Transaction transaction;
    private Urls urls;
    private Consumer consumer;
    private boolean threeDSecure;
    private boolean authorize;
    private boolean allowMultiCurrencyPurchase;
    private String transactionDate;
    private String description;
    private String destinationCountry;
    private int pax;
}