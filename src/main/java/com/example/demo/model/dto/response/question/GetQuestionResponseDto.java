package com.example.demo.model.dto.response.question;

import com.example.demo.model.dto.BasicPostDto;
import com.example.demo.model.dto.response.reply.GetReplyResponseDto;
import com.example.demo.model.entity.QuestionEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 Response DTO object for Get Question api call
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class GetQuestionResponseDto extends BasicPostDto {

    /**
     Long Id field
     */
    private Long id;
    /**
     Replies belonging to a question
     */
    private List<GetReplyResponseDto> replies;

    /**
     Mapper method to create GetQuestionResponseDto object from QuestionEntity

     @param questionEntity main questionEntity from which we want to create the dto
     @return GetQuestionResponseDto
     */
    public static GetQuestionResponseDto fromQuestionEntity(QuestionEntity questionEntity) {
        return GetQuestionResponseDto.builder()
                .id(questionEntity.getQuestionId())
                .author(questionEntity.getAuthor())
                .message(questionEntity.getMessage())
                .replies(questionEntity.getReplies()
                        .stream()
                        .map(replyEntity -> GetReplyResponseDto.fromReplyEntity(replyEntity))
                        .collect(Collectors.toList()))
                .build();
    }
}
