package za.co.paygenius.developer.paygeniusjavalibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {
    private String reference;
    private List<ToMerchant> toMerchants;
}