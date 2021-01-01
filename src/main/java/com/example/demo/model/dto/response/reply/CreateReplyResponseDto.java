package com.example.demo.model.dto.response.reply;

import com.example.demo.model.dto.BasicPostDto;
import com.example.demo.model.entity.ReplyEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 Response DTO object for Create Reply api call
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class CreateReplyResponseDto extends BasicPostDto {

    /**
     Long Id field
     */
    protected Long id;

    /**
     Long QuestionId foreign key field for reply object
     */
    private Long questionId;

    /**
     Mapper method to create CreateReplyResponseDto object from ReplyEntity

     @param replyEntity main replyEntity from which we want to create the dto
     @return CreateReplyResponseDto
     */
    public static CreateReplyResponseDto fromReplyEntity(ReplyEntity replyEntity) {
        return CreateReplyResponseDto.builder()
                .author(replyEntity.getAuthor())
                .message(replyEntity.getMessage())
                .id(replyEntity.getReplyId())
                .questionId(replyEntity.getQuestion().getQuestionId())
                .build();
    }

}
