package ru.otus.spring.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Класс Question")
public class QuestionTest {

    @DisplayName("корректно создается конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("answer1", "false"));
        answers.add(new Answer("answer2", "true"));

        var question = new Question("Question1", answers);

        assertThat(question.getText()).isEqualTo("Question1");
        assertThat(question.getAnswers()).isEqualTo(answers);
    }
}
