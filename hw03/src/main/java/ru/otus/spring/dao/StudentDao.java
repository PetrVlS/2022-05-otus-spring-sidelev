package ru.otus.spring.dao;

import ru.otus.spring.domain.Student;

public interface StudentDao {
    Student getStudent();

    void saveStudent(Student student);
}
