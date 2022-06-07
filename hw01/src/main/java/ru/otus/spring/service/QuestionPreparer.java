package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.MyIOException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class QuestionPreparer {
    private final QuestionDao questionDao;
    private final String resourceName;

    public QuestionPreparer(QuestionDao questionDao, String resourceName) {
        this.resourceName = resourceName;
        this.questionDao = questionDao;
    }

    public void prepareQuestions() {
        var lines = getAllLinesFromFile();
        lines.stream().map(this::convertStringToQuestion).forEach(questionDao::save);
    }

    private List<String> getAllLinesFromFile() {
        try (var reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(resourceName)))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new MyIOException("Error in reading data");
        }
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
