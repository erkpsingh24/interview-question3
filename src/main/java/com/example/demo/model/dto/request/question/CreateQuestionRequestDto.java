package com.example.demo.model.dto.request.question;

import com.example.demo.model.dto.BasicPostDto;
import com.example.demo.model.entity.QuestionEntity;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
public class CreateQuestionRequestDto extends BasicPostDto {

    public static QuestionEntity toQuestionEntity(BasicPostDto basicPostDto) {
        return QuestionEntity.builder().message(basicPostDto.getMessage()).author(basicPostDto.getAuthor()).build();
    }
}
