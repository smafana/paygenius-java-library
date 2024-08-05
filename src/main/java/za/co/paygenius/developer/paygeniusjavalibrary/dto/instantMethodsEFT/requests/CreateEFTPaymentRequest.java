package za.co.paygenius.developer.paygeniusjavalibrary.dto.instantMethodsEFT.requests;

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
public class CreateEFTPaymentRequest extends AbstractRequest {
    private Transaction transaction;
    private Consumer consumer;
    private Urls urls;

    public CreateEFTPaymentRequest(){
        super(Request.HttpMethod.POST, "v2/payment/create/eft");
    }
}
