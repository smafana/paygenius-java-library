package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.responses;

public class NonChallengedResponse {
    private boolean success;
    private String transactionDate;
    private String reference;
    private int code;
    private String message;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
