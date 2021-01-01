package com.example.demo;

import com.example.demo.model.dto.request.question.CreateQuestionRequestDto;
import com.example.demo.model.dto.request.reply.CreateReplyRequestDto;
import com.example.demo.model.entity.QuestionEntity;
import com.example.demo.model.entity.ReplyEntity;
import java.util.ArrayList;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class BaseTest {


    public static CreateQuestionRequestDto createQuestionRequestDto() {
        return CreateQuestionRequestDto.builder().message("message1").author("author1").build();
    }

    public static CreateReplyRequestDto createReplyRequestDto() {
        return CreateReplyRequestDto.builder().message("reply1").author("author1").build();
    }

    public static QuestionEntity createNewQuestion() {
        return QuestionEntity.builder().questionId(1L).author("Author1").message("Message1").replies(new ArrayList<>()).build();
    }

    public static ReplyEntity createReplyEntity() {
        return ReplyEntity.builder().author("reply-author").message("reply-message").replyId(1L).question(createNewQuestion()).build();
    }

}
