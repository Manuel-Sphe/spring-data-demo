package sphe.dev.cruddemo.dao;

import sphe.dev.cruddemo.entity.Student;

import java.util.UUID;

public interface StudentDAO {
    void save(Student student);

    Student findById(UUID id);
}
