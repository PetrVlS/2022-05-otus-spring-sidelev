package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.domain.Student;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс StudentDao")
public class StudentDaoTest {

    @DisplayName("корректно сохраняет и возвращает Student")
    @Test
    void shouldCorrectSaveAndGetStudent() {
        var student = new Student("Ivan", "Ivanov");
        var studentDao = new StudentDao();
        studentDao.saveStudent(student);
        assertThat(studentDao.getStudent()).isEqualTo(student);
    }
}
