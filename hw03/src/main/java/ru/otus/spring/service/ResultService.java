package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.StudentDao;
import ru.otus.spring.domain.Answer;

import java.util.Locale;

@Service
public class ResultService {
    private final StudentDao studentDao;
    private final IOService ioService;
    private final int scorePassing;
    private int numberOfCorrectAnswers;

    private final MessageSource messageSource;

    public ResultService(StudentDao studentDao, IOService ioService, @Value("${score.passing}") int scorePassing, MessageSource messageSource) {
        this.studentDao = studentDao;
        this.ioService = ioService;
        this.scorePassing = scorePassing;
        this.messageSource = messageSource;
        numberOfCorrectAnswers = 0;
    }


    public void showResult() {
        var student = studentDao.getStudent();
        if (numberOfCorrectAnswers >= scorePassing) {
            ioService.out(
                    String.format(messageSource.getMessage("test.passed.template", null, Locale.getDefault()),
                            student.getFirstName(), student.getLastName()));
        } else {
            ioService.out(
                    String.format(messageSource.getMessage("test.failed.template", null, Locale.getDefault()),
                            student.getFirstName(), student.getLastName()));
        }
    }

    public void checkAnswer(Answer answer) {
        if (answer.isCorrect()) {
            numberOfCorrectAnswers++;
        }
    }
}
