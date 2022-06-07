package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

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

        inOrder.verify(loggingService, times(1)).logging();
        inOrder.verify(testProcessor, times(1)).runTest();
        inOrder.verify(resultService, times(1)).showResult();
    }
}
