package com.example.demo.model.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 ErrorResponse class representing error responses when api calls fail
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    /*
    Integer statusCode
     */
    private int statusCode;

    /**
     String error Message
     */
    private String errorMessage;

    /**
     LocalDateTime Api response time
     */
    private LocalDateTime timestamp;
}
