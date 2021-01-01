package com.example.demo.service;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.dto.request.reply.CreateReplyRequestDto;
import com.example.demo.model.dto.response.reply.CreateReplyResponseDto;
import org.springframework.stereotype.Service;

/**
 Service contract for adding Replies to existing questions to the persistence layer
 */
@Service
public interface ReplyService {

    /**
     Add reply to an existing question in the persistence layer

     @param createReplyRequestDto reply dto object that needs to be stored in the db
     @param questionId            the target question id
     @return CreateReplyResponseDto
     @throws RecordNotFoundException thrown in case the target question is not present
     */
    CreateReplyResponseDto addReply(CreateReplyRequestDto createReplyRequestDto, Long questionId) throws RecordNotFoundException;
}
