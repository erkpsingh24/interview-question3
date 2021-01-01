package com.example.demo.dto.response;

import static org.junit.Assert.assertEquals;

import com.example.demo.BaseTest;
import com.example.demo.model.dto.response.question.GetQuestionsResponseDto;
import org.junit.jupiter.api.Test;

public class GetQuestionsResponseTest extends BaseTest {

    @Test
    public void testFromQuestionEntity() {
        GetQuestionsResponseDto getQuestionsResponseDto = GetQuestionsResponseDto.fromQuestionEntity(createNewQuestion());
        assertEquals(Long.valueOf(1), getQuestionsResponseDto.getId());
        assertEquals("Author1", getQuestionsResponseDto.getAuthor());
        assertEquals("Message1", getQuestionsResponseDto.getMessage());
    }
}
