package infrastructure.rest.error;


import javax.validation.Payload;

public abstract class ValidationErrorPayload implements Payload {
    private String errorCode;

    private String message;

    public ValidationErrorPayload(final String errorCode, final String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}

