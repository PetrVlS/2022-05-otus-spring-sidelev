package ru.otus.spring.dao;

import ru.otus.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionDao {
    private final List<Question> questions = new ArrayList<>();

    public void save(Question question) {
        questions.add(question);
    }

    public List<Question> findAll() {
        return questions;
    }
}
