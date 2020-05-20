package infrastructure.rest.error;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.stream.Collectors;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    private static final Logger LOG = LoggerFactory.getLogger(ConstraintViolationExceptionMapper.class);

    @Override
    public Response toResponse(ConstraintViolationException e) {
        LOG.warn("Request validation failed");
        List<ValidationErrorDTO> errors = e.getConstraintViolations().stream()
                .map(ValidationErrorDTO::new)
                .collect(Collectors.toList());

        return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();
    }
}

