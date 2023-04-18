package sphe.dev.restdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sphe.dev.restdemo.dao.StudentDAO;
import sphe.dev.restdemo.entity.Student;
import sphe.dev.restdemo.exception.exceptions.StudentNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImp implements StudentService {

    private final StudentDAO studentDAO;

    @Autowired
    public StudentServiceImp(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public void saveStudent(Student student) {
        studentDAO.saveStudent(student);
    }

    @Override
    public Student findById(UUID id){
      Student student = studentDAO.findById(id);

      if(student==null)
        throw new StudentNotFoundException("Student id not found - "+id.toString());
      return  student;
    }

    @Override
    public void update(Student newStudentInfo){
        studentDAO.update(newStudentInfo);
    }

    @Override
    public void delete(UUID id){
        studentDAO.delete(id);
    }


}
