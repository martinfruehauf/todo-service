package infrastructure.rest.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.bind.JsonbBuilder;
import javax.validation.ConstraintViolation;
import javax.validation.Payload;

public class ValidationErrorDTO {
    private String errorCode;

    private String message;

    private static final Logger LOG = LoggerFactory.getLogger(ValidationErrorDTO.class);

    public ValidationErrorDTO(final ConstraintViolation violation) {
        Class<? extends Payload> clazz = (Class<? extends Payload>)violation.getConstraintDescriptor().getPayload().iterator().next();
        ValidationErrorPayload payload = null;
        try {
            payload = (ValidationErrorPayload)clazz.newInstance();
            this.errorCode = payload.getErrorCode();
            this.message = payload.getMessage();
        } catch (InstantiationException | IllegalAccessException e) {
            LOG.error(e.getMessage(), e);
        }
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

    public String toString() {
        return JsonbBuilder.create().toJson(this);
    }
}
