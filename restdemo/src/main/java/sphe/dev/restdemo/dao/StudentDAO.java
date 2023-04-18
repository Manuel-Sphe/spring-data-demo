package sphe.dev.restdemo.dao;

import sphe.dev.restdemo.entity.Student;

import java.util.List;
import java.util.UUID;

public interface StudentDAO {

    void saveStudent(Student student);
    Student findById(UUID id);

    List<Student> findAll();

    //List<Student> findByName(String lastName, String firstName);

    void update(Student newStudentInfo);

    void delete(UUID id);

    //void delete(UUID id);

    //int deleteAll();
}
