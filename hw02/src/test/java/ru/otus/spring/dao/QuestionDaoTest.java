package ru.otus.spring.dao;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;


@ContextConfiguration
@TestPropertySource("classpath:application-test.properties")
@DisplayName("Класс QuestionDao")
public class QuestionDaoTest {

    @Value("${source.name}")
    private String resourceName;

    @Disabled
    @DisplayName("корректно возвращать Questions")
    @Test
    void shouldHaveCorrectFindQuestion() {

    }
}
