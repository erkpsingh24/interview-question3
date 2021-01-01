package com.example.demo.dto.response;

import static org.junit.Assert.assertEquals;

import com.example.demo.BaseTest;
import com.example.demo.model.dto.response.question.CreateQuestionResponseDto;
import org.junit.jupiter.api.Test;

public class CreateQuestionResponseTest extends BaseTest {

    @Test
    public void testFromQuestionEntity() {
        CreateQuestionResponseDto createQuestionResponseTest = CreateQuestionResponseDto.fromQuestionEntity(createNewQuestion());
        assertEquals(Long.valueOf(1), createQuestionResponseTest.getId());
        assertEquals("Author1", createQuestionResponseTest.getAuthor());
        assertEquals("Message1", createQuestionResponseTest.getMessage());
    }
}
