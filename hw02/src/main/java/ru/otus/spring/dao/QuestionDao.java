package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.MyIOException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionDao {
    private final String resourceName;

    public QuestionDao(@Value("${source.name}") String resourceName) {
        this.resourceName = resourceName;
    }

    public List<Question> findAll() {
        var lines = getAllLinesFromFile();
        var questions = lines.stream().map(this::convertStringToQuestion).collect(Collectors.toList());
        return questions;
    }

    private List<String> getAllLinesFromFile() {
        try (var reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(resourceName)))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new MyIOException("Error in reading data from file");
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
