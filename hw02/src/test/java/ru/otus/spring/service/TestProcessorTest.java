package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@DisplayName("Класс TestProcessor")
@ExtendWith(MockitoExtension.class)
public class TestProcessorTest {
    @Mock
    private IOService ioService;
    @Mock
    private QuestionDao questionDao;
    @Mock
    private ResultService resultService;

    @InjectMocks
    private TestProcessor testProcessor;

    private static List<Question> questions = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        List<Answer> answers1 = new ArrayList<>();
        answers1.add(new Answer("answer1", "false"));
        answers1.add(new Answer("answer2", "true"));

        var question1 = new Question("Question1", answers1);

        List<Answer> answers2 = new ArrayList<>();
        answers2.add(new Answer("answer3", "false"));
        answers2.add(new Answer("answer4", "true"));

        var question2 = new Question("Question2", answers2);

        questions.add(question1);
        questions.add(question2);
    }

    @DisplayName("корректно выполняет тест")
    @Test
    void shouldCorrectExecuteTest() {
        var inOrder = inOrder(questionDao, ioService, resultService);

        when(questionDao.findAll()).thenReturn(questions);
        when(ioService.readLine()).thenReturn("1");
        testProcessor.runTest();

        inOrder.verify(questionDao, times(1)).findAll();
        for (int i = 0; i < questions.size(); i++) {
            inOrder.verify(ioService, times(4)).out(any());
            inOrder.verify(ioService, times(1)).readLine();
            inOrder.verify(resultService, times(1)).checkAnswer(any());
        }
    }

}
