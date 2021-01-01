package com.example.demo.dto.response;

import static org.junit.Assert.assertEquals;

import com.example.demo.BaseTest;
import com.example.demo.model.dto.response.reply.CreateReplyResponseDto;
import org.junit.jupiter.api.Test;

public class CreateReplyResponseTest extends BaseTest {

    @Test
    public void testFromReplyEntity() {
        CreateReplyResponseDto createReplyResponseDto = CreateReplyResponseDto.fromReplyEntity(createReplyEntity());
        assertEquals("reply-author", createReplyResponseDto.getAuthor());
        assertEquals("reply-message", createReplyResponseDto.getMessage());
    }
}
