package ru.otus.spring.service;

import org.springframework.stereotype.Service;


@Service
public class ApplicationRunner {
    private final LoggingService loggingService;
    private final TestProcessor testProcessor;
    private final ResultService resultService;

    public ApplicationRunner(LoggingService loggingService, TestProcessor testProcessor, ResultService resultService) {
        this.loggingService = loggingService;
        this.testProcessor = testProcessor;
        this.resultService = resultService;
    }

    public void start() {
        loggingService.logging();
        testProcessor.runTest();
        resultService.showResult();
    }
}
