package com.target.myretail.exception;

import com.target.myretail.vo.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import static com.target.myretail.vo.CustomResponse.Status.FAIL;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductMisMatchException.class)
    public ResponseEntity<Object> handleExceptions(ProductMisMatchException exception, WebRequest webRequest) {
        CustomResponse response = new CustomResponse();
        response.setStatus(FAIL.name());
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Product id mis match");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(ProductNotFoundException exception, WebRequest webRequest) {
        CustomResponse response = new CustomResponse();
        response.setStatus(FAIL.name());
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Product not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
