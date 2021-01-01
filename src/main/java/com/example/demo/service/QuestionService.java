package com.example.demo.service;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.dto.request.question.CreateQuestionRequestDto;
import com.example.demo.model.dto.response.question.CreateQuestionResponseDto;
import com.example.demo.model.dto.response.question.GetQuestionResponseDto;
import com.example.demo.model.dto.response.question.GetQuestionsResponseDto;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 Service contract for adding and fetching Question entities
 */
@Service
public interface QuestionService {

    /**
     Get single question from a question id from persistence layer

     @param questionId primary key of the question that needs to be fetched
     @return GetQuestionResponseDto
     @throws RecordNotFoundException thrown in case the question id is not present in the db
     */
    Optional<GetQuestionResponseDto> getQuestion(Long questionId) throws RecordNotFoundException;

    /**
     Add a new question to the persistence layer

     @param question question dto object that needs to be stored in the db
     @return CreateQuestionResponseDto
     */
    CreateQuestionResponseDto addQuestion(CreateQuestionRequestDto question);

    /**
     Get all questions from the persistence layer

     @return List of GetQuestionResponseDto
     */
    List<GetQuestionsResponseDto> getAllQuestions();
}
