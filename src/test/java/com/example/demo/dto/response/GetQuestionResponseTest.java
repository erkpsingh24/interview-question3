package com.example.demo.dto.response;

import static org.junit.Assert.assertEquals;

import com.example.demo.BaseTest;
import com.example.demo.model.dto.response.question.GetQuestionResponseDto;
import org.junit.jupiter.api.Test;

public class GetQuestionResponseTest extends BaseTest {

    @Test
    public void testFromQuestionEntity() {
        GetQuestionResponseDto getQuestionResponseDto = GetQuestionResponseDto.fromQuestionEntity(createNewQuestion());
        assertEquals(Long.valueOf(1), getQuestionResponseDto.getId());
        assertEquals("Author1", getQuestionResponseDto.getAuthor());
        assertEquals("Message1", getQuestionResponseDto.getMessage());
    }

}
