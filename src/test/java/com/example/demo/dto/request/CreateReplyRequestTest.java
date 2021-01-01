package com.example.demo.dto.request;

import static org.junit.Assert.assertEquals;

import com.example.demo.BaseTest;
import com.example.demo.model.dto.request.reply.CreateReplyRequestDto;
import com.example.demo.model.entity.ReplyEntity;
import org.junit.jupiter.api.Test;

public class CreateReplyRequestTest extends BaseTest {

    @Test
    public void testFromReplyEntity() {
        ReplyEntity replyEntity = CreateReplyRequestDto.toReplyEntity(createReplyRequestDto());
        assertEquals("author1", replyEntity.getAuthor());
        assertEquals("reply1", replyEntity.getMessage());
    }

}
