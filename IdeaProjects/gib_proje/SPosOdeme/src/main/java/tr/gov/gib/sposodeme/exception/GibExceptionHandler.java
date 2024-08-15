package tr.gov.gib.sposodeme.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tr.gov.gib.gibcore.object.response.GibResponse;
import tr.gov.gib.gibcore.util.ServiceMessage;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GibExceptionHandler {


    @ExceptionHandler(GibException.class)
    public ResponseEntity<GibResponse> handleGibException(GibException ex) {
        ServiceMessage values = ex.getServiceMessage();
        List<Object> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        return new ResponseEntity<>(GibResponse.builder().service(values).error(errors).build(), HttpStatus.BAD_REQUEST);
    }
}
