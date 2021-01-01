package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.example.demo.BaseTest;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.dto.response.question.CreateQuestionResponseDto;
import com.example.demo.model.dto.response.question.GetQuestionResponseDto;
import com.example.demo.model.dto.response.question.GetQuestionsResponseDto;
import com.example.demo.model.entity.QuestionEntity;
import com.example.demo.persistence.QuestionRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

/**
 Unit tests for QuestionService Behaviour
 */

public class QuestionServiceTest extends BaseTest {

    @MockBean
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionService questionService;

    /**
     Tests GetOneQuestion method call in QuestionService for a Successful response

     @throws RecordNotFoundException
     */
    @Test
    @Transactional
    public void testGetOneQuestion() throws RecordNotFoundException {
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(createNewQuestion()));
        Optional<GetQuestionResponseDto> getQuestionResponseDto = questionService.getQuestion(1L);
        assertTrue(getQuestionResponseDto.isPresent());
        assertEquals("Author1", getQuestionResponseDto.get().getAuthor());
        assertEquals("Message1", getQuestionResponseDto.get().getMessage());
        assertEquals(new ArrayList<>(), getQuestionResponseDto.get().getReplies());
        assertEquals(Long.valueOf(1), getQuestionResponseDto.get().getId());
    }

    /**
     Tests testGetOneQuestion method call in QuestionService where the question entity has a reply for a Successful response

     @throws RecordNotFoundException
     */
    @Test
    @Transactional
    public void testGetOneQuestionWithReply() throws RecordNotFoundException {
        QuestionEntity questionEntityWithReply = createNewQuestion();
        questionEntityWithReply.setReplies(Arrays.asList(createReplyEntity()));
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(questionEntityWithReply));
        Optional<GetQuestionResponseDto> getQuestionResponseDto = questionService.getQuestion(1L);
        assertTrue(getQuestionResponseDto.isPresent());
        assertEquals("Author1", getQuestionResponseDto.get().getAuthor());
        assertEquals("Message1", getQuestionResponseDto.get().getMessage());
        assertEquals("reply-author", getQuestionResponseDto.get().getReplies().get(0).getAuthor());
        assertEquals("reply-message", getQuestionResponseDto.get().getReplies().get(0).getMessage());
        assertEquals(Long.valueOf(1), getQuestionResponseDto.get().getId());
    }

    /**
     Tests GetAllQuestions method call in QuestionService for a Successful response

     @throws RecordNotFoundException
     */

    @Test
    @Transactional
    public void testGetAllQuestions() {
        when(questionRepository.findAll()).thenReturn(Arrays.asList(createNewQuestion()));
        List<GetQuestionsResponseDto> getQuestionResponseDto = questionService.getAllQuestions();
        assertTrue(getQuestionResponseDto.size() > 0);
        assertEquals("Author1", getQuestionResponseDto.get(0).getAuthor());
        assertEquals("Message1", getQuestionResponseDto.get(0).getMessage());
        assertEquals(Long.valueOf(0), getQuestionResponseDto.get(0).getReplies());
        assertEquals(Long.valueOf(1), getQuestionResponseDto.get(0).getId());
    }


    /**
     Tests AddQuestion method call in QuestionService for a Successful response

     @throws RecordNotFoundException
     */
    @Test
    @Transactional
    public void testPostQuestion() {
        when(questionRepository.save(any(QuestionEntity.class))).thenReturn(createNewQuestion());
        CreateQuestionResponseDto createQuestionResponseDto = questionService.addQuestion(createQuestionRequestDto());
        assertEquals("Author1", createQuestionResponseDto.getAuthor());
        assertEquals("Message1", createQuestionResponseDto.getMessage());
        assertEquals(Long.valueOf(0), createQuestionResponseDto.getReplies());
        assertEquals(Long.valueOf(1), createQuestionResponseDto.getId());
    }


}
