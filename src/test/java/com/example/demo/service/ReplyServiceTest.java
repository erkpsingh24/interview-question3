package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.example.demo.BaseTest;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.dto.response.reply.CreateReplyResponseDto;
import com.example.demo.model.entity.ReplyEntity;
import com.example.demo.persistence.QuestionRepository;
import com.example.demo.persistence.ReplyRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

/**
 Unit tests for ReplyService Behaviour
 */
public class ReplyServiceTest extends BaseTest {

    @MockBean
    private ReplyRepository replyRepository;

    @MockBean
    private QuestionRepository questionRepository;

    @Autowired
    private ReplyService replyService;

    /**
     Tests AddReply to a Question method call in ReplyService for a Successful response

     @throws RecordNotFoundException
     */
    @Test
    @Transactional
    public void testPostReplyToQuestion() throws RecordNotFoundException {
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(createNewQuestion()));
        when(replyRepository.save(any(ReplyEntity.class))).thenReturn(createReplyEntity());
        CreateReplyResponseDto createReplyResponseDto = replyService.addReply(createReplyRequestDto(), 1L);
        assertEquals(Long.valueOf(1), createReplyResponseDto.getQuestionId());
        assertEquals(Long.valueOf(1), createReplyResponseDto.getId());
        assertEquals("reply-author", createReplyResponseDto.getAuthor());
        assertEquals("reply-message", createReplyResponseDto.getMessage());
    }

}
