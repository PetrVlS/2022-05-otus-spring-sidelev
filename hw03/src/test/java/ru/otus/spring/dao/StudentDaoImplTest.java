package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Student;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс StudentDaoImp")
public class StudentDaoImplTest {

    @DisplayName("корректно сохраняет и возвращает Student")
    @Test
    void shouldCorrectSaveAndGetStudent() {
        var student = new Student("Ivan", "Ivanov");
        var studentDao = new StudentDaoImpl();
        studentDao.saveStudent(student);
        assertThat(studentDao.getStudent()).isEqualTo(student);
    }
}
