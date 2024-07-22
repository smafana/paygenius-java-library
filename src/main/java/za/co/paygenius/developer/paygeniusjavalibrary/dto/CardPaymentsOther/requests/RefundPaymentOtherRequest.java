package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsOther.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.paygenius.developer.paygeniusjavalibrary.dto.Transaction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefundPaymentOtherRequest {
    private Transaction transaction;
}
