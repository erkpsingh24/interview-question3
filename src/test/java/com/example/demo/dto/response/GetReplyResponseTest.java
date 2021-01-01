package com.example.demo.dto.response;

import static org.junit.Assert.assertEquals;

import com.example.demo.BaseTest;
import com.example.demo.model.dto.response.reply.GetReplyResponseDto;
import org.junit.jupiter.api.Test;

public class GetReplyResponseTest extends BaseTest {

    @Test
    public void testFromReplyEntity() {
        GetReplyResponseDto getReplyResponseDto = GetReplyResponseDto.fromReplyEntity(createReplyEntity());
        assertEquals("reply-author", getReplyResponseDto.getAuthor());
        assertEquals("reply-message", getReplyResponseDto.getMessage());
    }

}
