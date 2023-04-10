package sphe.dev.cruddemo.dao;

import sphe.dev.cruddemo.entity.Student;

import java.util.List;
import java.util.UUID;

public interface StudentDAO {
    void save(Student student);

    Student findById(UUID id);

    List<Student> findAll();

    List<Student> findByName(String lastName, String firstName);

    void update(Student newStudentInfo);


}
