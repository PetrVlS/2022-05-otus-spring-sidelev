package ru.otus.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс Student")
public class StudentTest {

    @DisplayName("корректно создается конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        var student = new Student("Ivan", "Ivanov");

        assertThat(student.getFirstName()).isEqualTo("Ivan");
        assertThat(student.getLastName()).isEqualTo("Ivanov");
    }
}
