package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.MyIOException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class QuestionDaoImpl implements QuestionDao {
    private final String resourceName;

    @Value("${string.spliterator}")
    private String spliterator;

    private final MessageSource messageSource;

    public QuestionDaoImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
        this.resourceName = messageSource.getMessage("resource.name", null, Locale.getDefault());
    }

    @Override
    public List<Question> findAll() {
        var lines = getAllLinesFromFile();
        var questions = lines.stream().map(this::convertStringToQuestion).collect(Collectors.toList());
        return questions;
    }

    private List<String> getAllLinesFromFile() {
        try (var reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(resourceName)))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new MyIOException("Error in reading data from file", e);
        }
    }

    private Question convertStringToQuestion(String line) {
        String[] questionsWithAnswers = line.split(spliterator);
        var answers = new ArrayList<Answer>();
        answers.add(new Answer(questionsWithAnswers[1], Boolean.parseBoolean(questionsWithAnswers[2])));
        answers.add(new Answer(questionsWithAnswers[3], Boolean.parseBoolean(questionsWithAnswers[4])));
        answers.add(new Answer(questionsWithAnswers[5], Boolean.parseBoolean(questionsWithAnswers[6])));
        answers.add(new Answer(questionsWithAnswers[7], Boolean.parseBoolean(questionsWithAnswers[8])));
        return new Question(questionsWithAnswers[0], answers);
    }
}
