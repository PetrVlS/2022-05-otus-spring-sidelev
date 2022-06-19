package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.inOrder;

@DisplayName("Класс ApplicationRunner")
@ExtendWith(MockitoExtension.class)
public class ApplicationRunnerTest {
    @Mock
    private LoggingService loggingService;
    @Mock
    private TestProcessor testProcessor;
    @Mock
    private ResultService resultService;

    @InjectMocks
    private ApplicationRunner applicationRunner;

    @DisplayName("корректно запускает приложение")
    @Test
    void shouldCorrectStartApplication() {
        var inOrder = inOrder(loggingService, testProcessor, resultService);

        applicationRunner.start();

        inOrder.verify(loggingService).logging();
        inOrder.verify(testProcessor).runTest();
        inOrder.verify(resultService).showResult();
    }
}
