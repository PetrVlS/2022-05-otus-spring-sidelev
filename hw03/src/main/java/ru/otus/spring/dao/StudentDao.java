package ru.otus.spring.dao;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Student;

@Service
public class StudentDao {
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void saveStudent(Student student) {
        this.student = student;
    }
}
