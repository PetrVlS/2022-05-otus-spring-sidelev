package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dao.StudentDao;
import ru.otus.spring.domain.Student;

import static org.mockito.Mockito.*;

@DisplayName("Класс LoggingServiceStudent")
@ExtendWith(MockitoExtension.class)
public class LoggingServiceStudentTest {
    @Mock
    private IOService ioService;
    @Mock
    private StudentDao studentDao;

    @InjectMocks
    private LoggingServiceStudent loggingServiceStudent;

    @DisplayName("корректно выполняет логирование студента")
    @Test
    void shouldCorrectLoggingStudent() {
        var inOrder = inOrder(ioService, studentDao);
        when(ioService.readLine()).thenReturn("Ivan").thenReturn("Ivanov");

        loggingServiceStudent.logging();

        var student = new Student("Ivan", "Ivanov");

        inOrder.verify(ioService, times(1)).out("Input first name");
        inOrder.verify(ioService, times(1)).readLine();
        inOrder.verify(ioService, times(1)).out("Input last name");
        inOrder.verify(ioService, times(1)).readLine();
        inOrder.verify(studentDao, times(1)).saveStudent(student);
    }
}
