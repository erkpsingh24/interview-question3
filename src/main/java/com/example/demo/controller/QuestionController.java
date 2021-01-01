package com.example.demo.controller;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.dto.request.question.CreateQuestionRequestDto;
import com.example.demo.model.dto.request.reply.CreateReplyRequestDto;
import com.example.demo.model.dto.response.question.CreateQuestionResponseDto;
import com.example.demo.model.dto.response.reply.CreateReplyResponseDto;
import com.example.demo.model.dto.response.question.GetQuestionResponseDto;
import com.example.demo.model.dto.response.question.GetQuestionsResponseDto;
import com.example.demo.service.QuestionService;
import com.example.demo.service.ReplyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 API Controller to add and fetch Questions and Replies to the platform
 */
@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ReplyService replyService;

    /**
     API add new Question to the platform

     @param questionDto details of the question request containing the author name and message string
     @return createQuestionResponseDto object containing details of the posted Question in the required format
     */
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateQuestionResponseDto addQuestion(@RequestBody CreateQuestionRequestDto questionDto) {
        return questionService.addQuestion(questionDto);
    }

    /**
     API get Questions from the platform

     @param questionId the primary key of the question object to be fetched
     @return getQuestionResponseDto object containing details of the fetched Question in the required format
     @throws RecordNotFoundException thrown when the required object is not present in the database
     */
    @GetMapping("/{questionId}")
    @ResponseStatus(HttpStatus.OK)
    public GetQuestionResponseDto getQuestion(@PathVariable Long questionId) throws RecordNotFoundException {
        return questionService.getQuestion(questionId).orElseThrow(() -> new RecordNotFoundException("Question not found"));
    }

    /**
     API get all Questions from the platform

     @return list of getQuestionResponseDto iterable object containing details of all the fetched Questions in the required format
     */
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<GetQuestionsResponseDto> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    /**
     API add replies to existing questions to the platform

     @param replyRequestDto details of the reply request containing the author name and message string
     @param questionId      question id of the target question
     @return createReplyResponseDto object containing details of posted reply to a question in the required format
     @throws RecordNotFoundException thrown when the required question object is not present in the database
     */
    @PostMapping("/{questionId}/reply")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateReplyResponseDto addReplyToQuestion(@RequestBody CreateReplyRequestDto replyRequestDto, @PathVariable Long questionId)
            throws RecordNotFoundException {
        return replyService.addReply(replyRequestDto, questionId);
    }
}
