package za.co.paygenius.developer.paygeniusjavalibrary.dto.PaymentPageMethods.requests;

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
public class CreateRedirectRequest extends AbstractRequest{
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

    public CreateRedirectRequest(){
        super(Request.HttpMethod.POST, "v2/redirect/create");
    }
}
