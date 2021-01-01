package com.example.demo.model.dto.response.reply;

import com.example.demo.model.dto.BasicPostDto;
import com.example.demo.model.entity.ReplyEntity;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class GetReplyResponseDto extends BasicPostDto {

    /**
     Long Id field
     */
    private Long id;

    /**
     @param replyEntity main replyEntity from which we want to create the dto
     @return GetReplyResponseDto
     */
    public static GetReplyResponseDto fromReplyEntity(ReplyEntity replyEntity) {
        return GetReplyResponseDto.builder().id(replyEntity.getReplyId()).message(replyEntity.getMessage()).author(replyEntity.getAuthor()).build();
    }
}
