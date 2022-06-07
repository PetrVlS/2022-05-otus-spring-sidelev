package ru.otus.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс Answer")
public class AnswerTest {

    @DisplayName("корректно создается конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        var answer = new Answer("Answer", "true");

        assertThat(answer.getText()).isEqualTo("Answer");
        assertThat(answer.isCorrect()).isEqualTo(true);
    }
}
