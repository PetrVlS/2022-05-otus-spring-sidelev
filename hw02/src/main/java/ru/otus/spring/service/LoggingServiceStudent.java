package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.StudentDao;
import ru.otus.spring.domain.Student;

@Service
public class LoggingServiceStudent implements LoggingService {
    private final IOService ioService;
    private final StudentDao studentDao;

    public LoggingServiceStudent(IOService ioService, StudentDao studentDao) {
        this.ioService = ioService;
        this.studentDao = studentDao;
    }

    public void logging() {
        ioService.out("Input first name");
        var firstName = ioService.readLine();
        ioService.out("Input last name");
        var lastName = ioService.readLine();
        studentDao.saveStudent(new Student(firstName, lastName));
    }
}
