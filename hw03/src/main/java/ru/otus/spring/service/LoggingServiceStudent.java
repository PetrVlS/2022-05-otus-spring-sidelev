package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.StudentDao;
import ru.otus.spring.domain.Student;

import java.util.Locale;

@Service
public class LoggingServiceStudent implements LoggingService {
    private final IOService ioService;
    private final StudentDao studentDao;
    private final MessageSource messageSource;

    public LoggingServiceStudent(IOService ioService, StudentDao studentDao, MessageSource messageSource) {
        this.ioService = ioService;
        this.studentDao = studentDao;
        this.messageSource = messageSource;
    }

    public void logging() {
        ioService.out(messageSource.getMessage("input.first.name", null, Locale.getDefault()));
        var firstName = ioService.readLine();
        ioService.out(messageSource.getMessage("input.last.name", null, Locale.getDefault()));
        var lastName = ioService.readLine();
        studentDao.saveStudent(new Student(firstName, lastName));
    }
}
