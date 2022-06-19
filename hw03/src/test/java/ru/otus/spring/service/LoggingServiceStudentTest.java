package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import ru.otus.spring.dao.StudentDao;
import ru.otus.spring.domain.Student;

import java.util.Locale;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@DisplayName("Класс LoggingServiceStudent")
@ExtendWith(MockitoExtension.class)
public class LoggingServiceStudentTest {
    @Mock
    private IOService ioService;
    @Mock
    private StudentDao studentDao;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private LoggingServiceStudent loggingServiceStudent;

    @DisplayName("корректно выполняет логирование студента")
    @Test
    void shouldCorrectLoggingStudent() {
        var inOrder = inOrder(ioService, studentDao);
        when(ioService.readLine()).thenReturn("Ivan").thenReturn("Ivanov");
        when(messageSource.getMessage("input.first.name", null, Locale.getDefault())).thenReturn("Input first name");
        when(messageSource.getMessage("input.last.name", null, Locale.getDefault())).thenReturn("Input last name");

        loggingServiceStudent.logging();

        var student = new Student("Ivan", "Ivanov");

        inOrder.verify(ioService).out("Input first name");
        inOrder.verify(ioService).readLine();
        inOrder.verify(ioService).out("Input last name");
        inOrder.verify(ioService).readLine();
        inOrder.verify(studentDao).saveStudent(student);
    }
}
