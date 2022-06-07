package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
    private IOService ioService;

    private static final int SCORE_PASSING = 4;
    private static final String TEST_PASSED_TEMPLATE = "Student %s %s test passed";
    private static final String TEST_FAILED_TEMPLATE = "Student %s %s test failed";

    @DisplayName("корректно проверяет ответы")
    @Test
    void shouldCorrectCheckAnswer() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        var clazz = Class.forName("ru.otus.spring.service.ResultService");
        var numberOfCorrectAnswersField = clazz.getDeclaredField("numberOfCorrectAnswers");
        numberOfCorrectAnswersField.setAccessible(true);

        var resultService = new ResultService(studentDao, ioService, SCORE_PASSING);
        assertThat(numberOfCorrectAnswersField.get(resultService)).isEqualTo(0);

        var answer = new Answer("TrueAnswer", "true");
        resultService.checkAnswer(answer);
        assertThat(numberOfCorrectAnswersField.get(resultService)).isEqualTo(1);

        answer = new Answer("TrueAnswer", "false");
        resultService.checkAnswer(answer);
        assertThat(numberOfCorrectAnswersField.get(resultService)).isEqualTo(1);
    }

    @DisplayName("корректно отображает результат")
    @Test
    void shouldCorrectShowResult() {
        var inOrder = inOrder(studentDao, ioService);
        var resultService = new ResultService(studentDao, ioService, SCORE_PASSING);

        var student = new Student("Ivan", "Ivanov");
        when(studentDao.getStudent()).thenReturn(student);

        var correctAnswer = new Answer("CorrectAnswer", "true");
        for (int i = 0; i < SCORE_PASSING; i++) {
            resultService.checkAnswer(correctAnswer);
            resultService.showResult();
        }

        inOrder.verify(studentDao, times(1)).getStudent();
        inOrder.verify(ioService, times(SCORE_PASSING - 1)).out(String.format(TEST_FAILED_TEMPLATE, student.getFirstName(), student.getLastName()));
        inOrder.verify(ioService, times(1)).out(String.format(TEST_PASSED_TEMPLATE, student.getFirstName(), student.getLastName()));
    }
}
