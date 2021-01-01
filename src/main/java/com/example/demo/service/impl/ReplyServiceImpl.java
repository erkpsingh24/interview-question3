package com.example.demo.service.impl;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.dto.request.reply.CreateReplyRequestDto;
import com.example.demo.model.dto.response.reply.CreateReplyResponseDto;
import com.example.demo.model.entity.ReplyEntity;
import com.example.demo.persistence.QuestionRepository;
import com.example.demo.persistence.ReplyRepository;
import com.example.demo.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public CreateReplyResponseDto addReply(CreateReplyRequestDto replyRequestDto, Long questionId) throws RecordNotFoundException {
        ReplyEntity replyEntity = CreateReplyRequestDto.toReplyEntity(replyRequestDto);
        replyEntity.setQuestion(questionRepository.findById(questionId).orElseThrow(() -> new RecordNotFoundException("Invalid question")));
        return CreateReplyResponseDto.fromReplyEntity(replyRepository.save(replyEntity));
    }
}
