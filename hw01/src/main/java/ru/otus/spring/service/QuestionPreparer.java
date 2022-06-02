package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class QuestionPreparer {
    private final BufferedReader reader;
    private final QuestionDao questionDao;

    public QuestionPreparer(QuestionDao questionDao, String resourceName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName);
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        this.questionDao = questionDao;
    }

    public void prepareQuestions() {
        var lines = getAllLinesFromFile();
        lines.stream().map(this::convertStringToQuestion).forEach(questionDao::save);
    }

    private List<String> getAllLinesFromFile() {
        return reader.lines().collect(Collectors.toList());
    }

    private Question convertStringToQuestion(String line) {
        String[] questionsWithAnswers = line.split(";");
        var answers = new ArrayList<Answer>();
        answers.add(new Answer(questionsWithAnswers[1], questionsWithAnswers[2]));
        answers.add(new Answer(questionsWithAnswers[3], questionsWithAnswers[4]));
        answers.add(new Answer(questionsWithAnswers[5], questionsWithAnswers[6]));
        answers.add(new Answer(questionsWithAnswers[7], questionsWithAnswers[8]));
        return new Question(questionsWithAnswers[0], answers);
    }
}
