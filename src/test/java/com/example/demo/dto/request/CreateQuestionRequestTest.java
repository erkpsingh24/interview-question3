package com.example.demo.dto.request;

import static org.junit.Assert.assertEquals;

import com.example.demo.BaseTest;
import com.example.demo.model.dto.request.question.CreateQuestionRequestDto;
import com.example.demo.model.entity.QuestionEntity;
import org.junit.jupiter.api.Test;

public class CreateQuestionRequestTest extends BaseTest {

    @Test
    public void testFromQuestionEntity() {
        QuestionEntity questionEntity = CreateQuestionRequestDto.toQuestionEntity(createQuestionRequestDto());
        assertEquals("author1", questionEntity.getAuthor());
        assertEquals("message1", questionEntity.getMessage());
    }
}
