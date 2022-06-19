package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс IOServiceConsole")
public class IOServiceConsoleTest {

    private ByteArrayOutputStream bos;
    private IOService ioService;

    @BeforeEach
    void setUp() {
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        ioService = new IOServiceConsole();
    }

    @DisplayName("должен корректно выводить в консоль")
    @Test
    void shouldCorrectOutToConsole() {
        ioService.out("test");
        assertThat(bos.toString()).isEqualTo("test" + System.lineSeparator());
    }
}
