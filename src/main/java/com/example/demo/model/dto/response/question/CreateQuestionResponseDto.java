package com.example.demo.model.dto.response.question;

import com.example.demo.model.dto.BasicPostDto;
import com.example.demo.model.entity.QuestionEntity;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 Response DTO object for Create Question api call
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class CreateQuestionResponseDto extends BasicPostDto {

    /**
     Long Id field
     */
    protected Long id;

    /**
     Long representing the number of replies for a question
     */
    private Long replies;

    /**
     Mapper method to create CreateQuestionResponseDto object from QuestionEntity

     @param questionEntity main questionEntity from which we want to create the dto
     @return CreateQuestionResponseDto
     */
    public static CreateQuestionResponseDto fromQuestionEntity(QuestionEntity questionEntity) {
        return CreateQuestionResponseDto.builder()
                .id(questionEntity.getQuestionId())
                .author(questionEntity.getAuthor())
                .message(questionEntity.getMessage())
                .replies(Long.valueOf(Optional.ofNullable(questionEntity.getReplies()).map(List::size).orElse(0)))
                .build();
    }
}
