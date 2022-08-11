package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.otus.spring.dao.StudentDao;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@DisplayName("Класс ResultService")
@ExtendWith(MockitoExtension.class)
public class ResultServiceTest {
    @Mock
    private StudentDao studentDao;

    @Mock
    private MessageSource messageSource;

    @Mock
    private IOService ioService;

    private static final int SCORE_PASSING = 4;

    @DisplayName("корректно проверяет ответы")
    @Test
    void shouldCorrectCheckAnswer() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        var clazz = Class.forName("ru.otus.spring.service.ResultService");
        var numberOfCorrectAnswersField = clazz.getDeclaredField("numberOfCorrectAnswers");
        numberOfCorrectAnswersField.setAccessible(true);

        var resultService = new ResultService(studentDao, ioService, SCORE_PASSING, messageSource);
        assertThat(numberOfCorrectAnswersField.get(resultService)).isEqualTo(0);

        var answer = new Answer("TrueAnswer", true);
        resultService.checkAnswer(answer);
        assertThat(numberOfCorrectAnswersField.get(resultService)).isEqualTo(1);

        answer = new Answer("TrueAnswer", false);
        resultService.checkAnswer(answer);
        assertThat(numberOfCorrectAnswersField.get(resultService)).isEqualTo(1);
    }

    @DisplayName("корректно отображает результат")
    @Test
    void shouldCorrectShowResult() {
        var inOrder = inOrder(studentDao, ioService);
        var resultService = new ResultService(studentDao, ioService, SCORE_PASSING, messageSource);

        var student = new Student("Ivan", "Ivanov");
        when(studentDao.getStudent()).thenReturn(student);
        when(messageSource.getMessage(any(), any(), any())).thenReturn("%s %s");

        var correctAnswer = new Answer("CorrectAnswer", true);
        for (int i = 0; i < SCORE_PASSING; i++) {
            resultService.checkAnswer(correctAnswer);
            resultService.showResult();
        }

        inOrder.verify(studentDao).getStudent();
        inOrder.verify(ioService, times(SCORE_PASSING)).out(contains("Ivan Ivanov"));
    }
}
