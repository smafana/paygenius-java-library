package za.co.paygenius.developer.paygeniusjavalibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private String reference;
    private String currency;
    private BigDecimal amount;
}
