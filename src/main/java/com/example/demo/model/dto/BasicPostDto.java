package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BasicPostDto {

    /**
     String Author field
     */
    protected String author;

    /**
     String Message field
     */
    protected String message;


}
