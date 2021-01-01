package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.example.demo.BaseTest;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.dto.request.question.CreateQuestionRequestDto;
import com.example.demo.model.dto.request.reply.CreateReplyRequestDto;
import com.example.demo.model.dto.response.question.CreateQuestionResponseDto;
import com.example.demo.model.dto.response.question.GetQuestionResponseDto;
import com.example.demo.model.dto.response.question.GetQuestionsResponseDto;
import com.example.demo.model.dto.response.reply.CreateReplyResponseDto;
import com.example.demo.service.QuestionService;
import com.example.demo.service.ReplyService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

/**
 Unit tests for Controller Layer when we mock the Service layer
 */

public class QuestionControllerTest extends BaseTest {

    @Autowired
    QuestionController questionController;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private ReplyService replyService;

    /**
     check if the controller bean is configured correctly
     */
    @Test
    public void contextLoads() {
        assertThat(questionController).isNotNull();
    }

    /**
     Check the response of GetOneQuestion api in Controller

     @throws RecordNotFoundException
     */
    @Test
    @Transactional
    public void testGetOneQuestion() throws RecordNotFoundException {
        when(questionService.getQuestion(anyLong())).thenReturn(Optional.of(GetQuestionResponseDto.fromQuestionEntity(createNewQuestion())));
        GetQuestionResponseDto getQuestionResponseDto = questionController.getQuestion(1L);
        assertEquals("Author1", getQuestionResponseDto.getAuthor());
        assertEquals("Message1", getQuestionResponseDto.getMessage());
        assertEquals(new ArrayList<>(), getQuestionResponseDto.getReplies());
    }

    /**
     /** Check the response of GetAllQuestions api in Controller
     */

    @Test
    @Transactional
    public void testGetAllQuestions() {
        when(questionService.getAllQuestions()).thenReturn(Arrays.asList(GetQuestionsResponseDto.fromQuestionEntity(createNewQuestion())));
        Assert.assertNotEquals(questionController.getAllQuestions().size(), 0);
        List<GetQuestionsResponseDto> listOfQuestions = questionController.getAllQuestions();
        assertEquals("Author1", listOfQuestions.get(0).getAuthor());
        assertEquals("Message1", listOfQuestions.get(0).getMessage());
        assertEquals(Long.valueOf(0), listOfQuestions.get(0).getReplies());
    }

    /**
     Check the response of addQuestion api in Controller
     */
    @Test
    @Transactional
    public void testPostQuestion() {
        when(questionService.addQuestion(any(CreateQuestionRequestDto.class))).thenReturn(
                CreateQuestionResponseDto.fromQuestionEntity(createNewQuestion()));
        CreateQuestionResponseDto createQuestionResponseDto = questionController.addQuestion(createQuestionRequestDto());
        assertEquals("Author1", createQuestionResponseDto.getAuthor());
        assertEquals("Message1", createQuestionResponseDto.getMessage());
        assertEquals(Long.valueOf(0), createQuestionResponseDto.getReplies());
    }

    /**
     Check the response of PostNewReply to a question api in Controller
     */
    @Test
    @Transactional
    public void testPostReplyToQuestion() throws RecordNotFoundException {
        when(replyService.addReply(any(CreateReplyRequestDto.class), anyLong())).thenReturn(
                CreateReplyResponseDto.fromReplyEntity(createReplyEntity()));
        CreateReplyResponseDto createReplyResponseDto = questionController.addReplyToQuestion(createReplyRequestDto(), 1L);
        assertEquals("reply-author", createReplyResponseDto.getAuthor());
        assertEquals("reply-message", createReplyResponseDto.getMessage());
    }

}
