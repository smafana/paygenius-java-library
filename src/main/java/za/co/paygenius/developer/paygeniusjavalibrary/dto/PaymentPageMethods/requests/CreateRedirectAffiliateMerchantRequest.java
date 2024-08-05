package za.co.paygenius.developer.paygeniusjavalibrary.dto.PaymentPageMethods.requests;

import feign.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.requests.CreatePaymentOtherRequest;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.Consumer;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.Transaction;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.Transfer;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.Urls;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.request.AbstractRequest;

@Getter
@Setter
public class CreateRedirectAffiliateMerchantRequest extends AbstractRequest {
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

    public CreateRedirectAffiliateMerchantRequest(){
        super(Request.HttpMethod.POST, "v2/redirect/create");
    }
}
