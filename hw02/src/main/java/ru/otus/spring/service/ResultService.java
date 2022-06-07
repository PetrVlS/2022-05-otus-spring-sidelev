package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.StudentDao;
import ru.otus.spring.domain.Answer;

@Service
public class ResultService {
    private final StudentDao studentDao;
    private final IOService ioService;
    private final int scorePassing;
    private int numberOfCorrectAnswers;
    private static final String TEST_PASSED_TEMPLATE = "Student %s %s test passed";
    private static final String TEST_FAILED_TEMPLATE = "Student %s %s test failed";

    public ResultService(StudentDao studentDao, IOService ioService, @Value("${score.passing}") int scorePassing) {
        this.studentDao = studentDao;
        this.ioService = ioService;
        this.scorePassing = scorePassing;
        numberOfCorrectAnswers = 0;
    }


    public void showResult() {
        var student = studentDao.getStudent();
        if (numberOfCorrectAnswers >= scorePassing) {
            ioService.out(String.format(TEST_PASSED_TEMPLATE, student.getFirstName(), student.getLastName()));
        } else {
            ioService.out(String.format(TEST_FAILED_TEMPLATE, student.getFirstName(), student.getLastName()));
        }
    }

    public void checkAnswer(Answer answer) {
        if (answer.isCorrect()) {
            numberOfCorrectAnswers++;
        }
    }
}
