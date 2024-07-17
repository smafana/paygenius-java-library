package za.co.paygenius.developer.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerAddress {
    private String addressLineOne;
    private String city;
    private String postCode;
    private String country;
}
