package imb.gc4.turnero.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import imb.gc4.turnero.util.APIResponse;
import imb.gc4.turnero.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;
import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request){
        return ResponseUtil.handleConstraintException(ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Object>> handleGeneralException(Exception ex, WebRequest request) {
        APIResponse<Object> response = new APIResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),Collections.singletonList(ex.getMessage()), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}

