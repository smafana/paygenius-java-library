package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.requests;

import feign.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.Consumer;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.Transaction;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.Urls;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.request.AbstractRequest;

@Getter
@Setter
//@AllArgsConstructor
public class CreatePaymentOtherRequest extends AbstractRequest {
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

    public CreatePaymentOtherRequest(){
        super(Request.HttpMethod.POST, "/v3/payment/create");
    }
}