package sphe.dev.restdemo.service;

import sphe.dev.restdemo.entity.Student;

import java.util.List;
import java.util.UUID;

public interface StudentService {


    List<Student> findAll();

    void save(Student student);

    Student findById(UUID id);

    void updateStudent(Student newStudentInfo);

    void deleteById(UUID id);

}
