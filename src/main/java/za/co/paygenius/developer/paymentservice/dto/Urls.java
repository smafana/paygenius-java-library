package za.co.paygenius.developer.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Urls {
    private String success;
    private String cancel;
    private String error;
    private String notify;
}
