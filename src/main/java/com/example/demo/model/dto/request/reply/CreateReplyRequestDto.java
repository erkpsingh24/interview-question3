package com.example.demo.model.dto.request.reply;

import com.example.demo.model.dto.BasicPostDto;
import com.example.demo.model.entity.ReplyEntity;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
public class CreateReplyRequestDto extends BasicPostDto {

    public static ReplyEntity toReplyEntity(BasicPostDto basicPostDto) {
        return ReplyEntity.builder().message(basicPostDto.getMessage()).author(basicPostDto.getAuthor()).build();
    }
}
