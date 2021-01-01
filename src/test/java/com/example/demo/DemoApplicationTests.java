package com.example.demo;

import static com.example.demo.BaseTest.createQuestionRequestDto;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import com.example.demo.model.dto.BasicPostDto;
import com.example.demo.model.dto.response.ErrorResponse;
import com.example.demo.model.dto.response.question.CreateQuestionResponseDto;
import com.example.demo.model.dto.response.question.GetQuestionResponseDto;
import com.example.demo.model.dto.response.reply.CreateReplyResponseDto;
import com.example.demo.model.dto.response.reply.GetReplyResponseDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 Integration tests for the application, where the application is ran on a certain port and different apis are hit to check for expected response
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
public class DemoApplicationTests {

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;

    public ResponseEntity<CreateQuestionResponseDto> postNewQuestion() {
        HttpEntity<BasicPostDto> entity = new HttpEntity<>(createQuestionRequestDto(), headers);
        return restTemplate.exchange(createURLWithPort("/questions/"), HttpMethod.POST, entity, CreateQuestionResponseDto.class);

    }

    /**
     Calls GET "/questions/{questionId}" api to check if we are able to receive correct format and response
     */
    @Test
    public void testGetOneQuestion() {
        postNewQuestion();
        HttpEntity<String> entity1 = new HttpEntity<String>(null, headers);
        ResponseEntity<GetQuestionResponseDto> response = restTemplate.exchange(createURLWithPort("/questions/1"), HttpMethod.GET, entity1,
                GetQuestionResponseDto.class);
        assertEquals(OK, response.getStatusCode());
        assertEquals("author1", response.getBody().getAuthor());
        assertEquals("message1", response.getBody().getMessage());
        assertEquals(new ArrayList<GetReplyResponseDto>(), response.getBody().getReplies());
    }

    /**
     Calls GET "/questions/{questionId}" api to check if we are able to receive correct format and response in case when record isnt found
     */
    @Test
    public void testGetOneQuestionException() {
        HttpEntity<String> entity1 = new HttpEntity<String>(null, headers);
        ResponseEntity<ErrorResponse> response = restTemplate.exchange(createURLWithPort("/questions/1"), HttpMethod.GET, entity1,
                ErrorResponse.class);
        assertEquals(NOT_FOUND, response.getStatusCode());
        assertEquals("Question not found", response.getBody().getErrorMessage());
        assertEquals(404, response.getBody().getStatusCode());
    }

    /**
     Calls GET "/questions/" api to check if we are able to receive correct format and response for all questions in the db
     */
    @Test
    public void testGetAllQuestions() {
        postNewQuestion();
        postNewQuestion();
        HttpEntity<String> entity1 = new HttpEntity<String>(null, headers);
        ResponseEntity<List> response = restTemplate.exchange(createURLWithPort("/questions"), HttpMethod.GET, entity1, List.class);
        Map<String, Object> question1 = (Map) response.getBody().get(0);
        Map<String, Object> question2 = (Map) response.getBody().get(1);

        assertEquals(OK, response.getStatusCode());
        assertEquals("author1", question1.get("author"));
        assertEquals(1, question1.get("id"));
        assertEquals("message1", question1.get("message"));
        assertEquals(0, question1.get("replies"));

        assertEquals(2, question2.get("id"));
        assertEquals("author1", question2.get("author"));
        assertEquals("message1", question2.get("message"));
        assertEquals(0, question2.get("replies"));
    }

    /**
     Calls POST "/questions/{questionId}/reply" api to check if we are able to receive correct format and response when we post a new reply for a
     question to the api
     */
    @Test
    public void testPostReplyToQuestion() {
        postNewQuestion();

        HttpEntity<GetReplyResponseDto> entity = new HttpEntity<>(GetReplyResponseDto.builder().message("message1").author("new author").build(),
                headers);
        ResponseEntity<CreateReplyResponseDto> response = restTemplate.exchange(createURLWithPort("/questions/1/reply"), HttpMethod.POST, entity,
                CreateReplyResponseDto.class);
        assertEquals(CREATED, response.getStatusCode());
        assertEquals(Long.valueOf(1), response.getBody().getQuestionId());
        assertEquals("new author", response.getBody().getAuthor());
        assertEquals("message1", response.getBody().getMessage());
        assertEquals(Long.valueOf(2), response.getBody().getId());
    }

    /**
     Calls POST "/questions/" api to check if we are able to receive correct format and response when we post a new question to the api
     */
    @Test
    public void testPostQuestion() {
        ResponseEntity<CreateQuestionResponseDto> response = postNewQuestion();
        assertEquals(CREATED, response.getStatusCode());
        assertEquals("author1", response.getBody().getAuthor());
        assertEquals("message1", response.getBody().getMessage());
        assertEquals(Long.valueOf(0), response.getBody().getReplies());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
