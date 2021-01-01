package com.example.demo.exception.handler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.dto.response.ErrorResponse;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 Controller Advice to return custom error response in case of failed Api calls
 */
@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     Handle Api response when RecordNotFoundException is thrown

     @param recordNotFoundException thrown in case the target question is not present
     @return ErrorResponse the decided format for non-success api calls
     */

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ErrorResponse handleRecordNotFoundException(RecordNotFoundException recordNotFoundException) {
        return ErrorResponse.builder()
                .statusCode(NOT_FOUND.value())
                .errorMessage(recordNotFoundException.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     Generic handler for all other exceptions
     @param exception thrown
     @return ErrorResponse
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    protected ErrorResponse handleRecordNotFoundException(Exception exception) {
        return ErrorResponse.builder()
                .statusCode(INTERNAL_SERVER_ERROR.value())
                .errorMessage(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
