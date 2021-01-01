package com.example.demo.model.dto.response.question;

import com.example.demo.model.entity.QuestionEntity;
import java.util.List;
import java.util.Optional;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class GetQuestionsResponseDto extends CreateQuestionResponseDto {

    /**
     Mapper method to create GetQuestionsResponseDto object from QuestionEntity

     @param questionEntity main questionEntity from which we want to create the dto
     @return GetQuestionsResponseDto
     */
    public static GetQuestionsResponseDto fromQuestionEntity(QuestionEntity questionEntity) {
        return GetQuestionsResponseDto.builder()
                .id(questionEntity.getQuestionId())
                .author(questionEntity.getAuthor())
                .message(questionEntity.getMessage())
                .replies(Long.valueOf(Optional.ofNullable(questionEntity.getReplies()).map(List::size).orElse(0)))
                .build();
    }
}
