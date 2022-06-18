package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionDao;


@Service
public class TestProcessor {
    private final IOService ioService;
    private final ResultService resultService;
    private final QuestionDao questionDao;
    private static final String QUESTION_STRING_TEMPLATE = "Question #%d: %s";
    private static final String ANSWER_STRING_TEMPLATE = "%d. %s";


    public TestProcessor(IOService ioService, ResultService resultService, QuestionDao questionDao) {
        this.ioService = ioService;
        this.resultService = resultService;
        this.questionDao = questionDao;
    }

    public void runTest() {
        var questions = questionDao.findAll();
        for (int i = 0; i < questions.size(); i++) {
            print(QUESTION_STRING_TEMPLATE, i + 1, questions.get(i).getText());
            var answers = questions.get(i).getAnswers();
            for (int j = 0; j < answers.size(); j++) {
                print(ANSWER_STRING_TEMPLATE, j + 1, answers.get(j).getText());
            }
            print("Enter answer number");
            var answerNumber = getAnswerNumber();
            resultService.checkAnswer(answers.get(answerNumber - 1));
        }
    }

    private void print(String template, Object... args) {
        ioService.out(String.format(template, args));
    }

    private int getAnswerNumber() {
        return Integer.parseInt(ioService.readLine());
    }


}
