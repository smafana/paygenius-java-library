package za.co.paygenius.developer.paymentservice.dto.CardPaymentsOther;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Consumer {
    private String name;
    private String surname;
    private String email;
    private ConsumerAddress consumerAddress;
}
