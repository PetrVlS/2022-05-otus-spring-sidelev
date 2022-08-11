package ru.otus.spring.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Student;

@Component
public class StudentDaoImpl implements StudentDao {
    private Student student;

    @Override
    public Student getStudent() {
        return student;
    }

    @Override
    public void saveStudent(Student student) {
        this.student = student;
    }
}
