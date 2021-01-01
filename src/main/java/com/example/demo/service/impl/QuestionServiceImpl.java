package com.example.demo.service.impl;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.dto.request.question.CreateQuestionRequestDto;
import com.example.demo.model.dto.response.question.CreateQuestionResponseDto;
import com.example.demo.model.dto.response.question.GetQuestionResponseDto;
import com.example.demo.model.dto.response.question.GetQuestionsResponseDto;
import com.example.demo.persistence.QuestionRepository;
import com.example.demo.service.QuestionService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Optional<GetQuestionResponseDto> getQuestion(Long questionId) throws RecordNotFoundException {
        return Optional.of(GetQuestionResponseDto.fromQuestionEntity(
                questionRepository.findById(questionId).orElseThrow(() -> new RecordNotFoundException("Question not found"))));
    }

    @Override
    public CreateQuestionResponseDto addQuestion(CreateQuestionRequestDto question) {
        return CreateQuestionResponseDto.fromQuestionEntity(questionRepository.save(CreateQuestionRequestDto.toQuestionEntity(question)));
    }

    @Override
    public List<GetQuestionsResponseDto> getAllQuestions() {
        return questionRepository.findAll()
                .stream()
                .map(questionEntity -> GetQuestionsResponseDto.fromQuestionEntity(questionEntity))
                .collect(Collectors.toList());
    }
}
