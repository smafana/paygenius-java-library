package za.co.paygenius.developer.paygeniusjavalibrary.dto.PaymentPageMethods.requests;

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
public class CreateRedirectRequest {
    private Transaction transaction;
    private Consumer consumer;
    private Urls urls;
    private boolean auth;
    private String pageUrlKey;
    private boolean threeDSecure;
    private String description;
    private String destinationCountry;
    private String transactionDate;
    private int pax;
    private int affiliateMerchantId;
    private boolean recurring;
}
