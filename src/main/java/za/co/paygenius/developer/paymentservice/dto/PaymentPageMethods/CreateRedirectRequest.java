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
