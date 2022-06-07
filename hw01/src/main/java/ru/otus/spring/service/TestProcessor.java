package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

public class TestProcessor {
    private final IOService ioService;
    private final QuestionDao questionDao;
    private final QuestionPreparer questionPreparer;

    public TestProcessor(IOService ioService, QuestionDao questionDao, QuestionPreparer questionPreparer) {
        this.ioService = ioService;
        this.questionDao = questionDao;
        this.questionPreparer = questionPreparer;
    }

    public void startTest() {
        questionPreparer.prepareQuestions();
        printQuestionsWithAnswers();
    }

    private void printQuestionsWithAnswers() {
        var questions = questionDao.findAll();
        for (int i = 0; i < questions.size(); i++) {
            ioService.out(String.format("Question #%d: %s", i + 1, questions.get(i).getText()));
            printAnswers(questions.get(i));
        }
    }

    private void printAnswers(Question question) {
        var answers = question.getAnswers();
        for (int i = 0; i < answers.size(); i++) {
            ioService.out(String.format("%d. %s", i + 1, answers.get(i).getText()));
        }
    }

}
