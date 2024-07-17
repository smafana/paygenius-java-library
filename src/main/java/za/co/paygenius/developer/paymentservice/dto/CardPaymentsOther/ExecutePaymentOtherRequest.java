package za.co.paygenius.developer.paymentservice.dto.CardPaymentsOther;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.paygenius.developer.paymentservice.dto.Transaction;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExecutePaymentOtherRequest {
    private Transaction transaction;
}
