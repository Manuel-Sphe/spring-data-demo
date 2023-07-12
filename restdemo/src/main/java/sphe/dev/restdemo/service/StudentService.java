package sphe.dev.restdemo.service;

import sphe.dev.restdemo.entity.Student;
import sphe.dev.restdemo.service.dto.StudentDTO;

import java.util.List;
import java.util.UUID;

public interface StudentService {

    /**
     *
     * @param studentDTO student to be saved
     * @return the persisted entity
     */
    StudentDTO save(StudentDTO studentDTO);

    List<Student> findAll();

    /**
     * @param student
     */
    void saveStudent(Student student);

    Student findById(UUID id);

    void updateStudent(Student newStudentInfo);

    void delete(UUID id);



}
