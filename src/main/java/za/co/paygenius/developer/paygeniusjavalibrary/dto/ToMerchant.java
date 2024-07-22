package za.co.paygenius.developer.paygeniusjavalibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToMerchant {
    private String currency;
    private int amount;
    private int merchantId;
    private String merchantReference;
}
