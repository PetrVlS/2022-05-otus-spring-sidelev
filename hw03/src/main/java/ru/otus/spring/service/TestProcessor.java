package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionDao;

import java.util.Locale;

@Service
public class TestProcessor {
    private final IOService ioService;
    private final ResultService resultService;
    private final QuestionDao questionDao;
    private final MessageSource messageSource;

    public TestProcessor(IOService ioService, ResultService resultService, QuestionDao questionDao, MessageSource messageSource) {
        this.ioService = ioService;
        this.resultService = resultService;
        this.questionDao = questionDao;
        this.messageSource = messageSource;
    }

    public void runTest() {
        var questions = questionDao.findAll();
        for (int i = 0; i < questions.size(); i++) {
            print(messageSource.getMessage("question.string.template", null, Locale.getDefault()), i + 1, questions.get(i).getText());
            var answers = questions.get(i).getAnswers();
            for (int j = 0; j < answers.size(); j++) {
                print(messageSource.getMessage("answer.string.template", null, Locale.getDefault()), j + 1, answers.get(j).getText());
            }

            int answerNumber;
            try {
                print(messageSource.getMessage("enter.answer.number", null, Locale.getDefault()));
                answerNumber = getAnswerNumber();
                if (answerNumber > answers.size() || answerNumber < 1) {
                    throw new RuntimeException();
                }
            } catch (RuntimeException e) {
                print(messageSource.getMessage("invalid.input.data", null, Locale.getDefault()));
                print(messageSource.getMessage("enter.answer.number", null, Locale.getDefault()));
                answerNumber = getAnswerNumber();
            }
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
