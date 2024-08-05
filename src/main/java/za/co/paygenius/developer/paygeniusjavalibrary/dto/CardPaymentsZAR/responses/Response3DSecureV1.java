package za.co.paygenius.developer.paygeniusjavalibrary.dto.CardPaymentsZAR.responses;

public class Response3DSecureV1 {
    public boolean success;
    public String message;
    public String transactionDate;
    public String originalErrorCode;
    public int code;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getOriginalErrorCode() {
        return originalErrorCode;
    }

    public void setOriginalErrorCode(String originalErrorCode) {
        this.originalErrorCode = originalErrorCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
