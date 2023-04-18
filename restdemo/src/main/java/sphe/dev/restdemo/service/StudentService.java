package sphe.dev.restdemo.service;

import sphe.dev.restdemo.entity.Student;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    List<Student> findAll();

    void saveStudent(Student student);

    Student findById(UUID id);

    void update(Student newStudentInfo);

    void delete(UUID id);
}
