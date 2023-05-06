package sphe.dev.restdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sphe.dev.restdemo.dao.StudentRepository;
import sphe.dev.restdemo.entity.Student;
import sphe.dev.restdemo.exception.exceptions.StudentNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentServiceImp implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImp(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override

    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student findById(UUID id){
      Optional<Student> results = studentRepository.findById(id);

      Student theStudent = null;
      if(results.isPresent())
          theStudent = results.get();
      else{
          throw new RuntimeException("Did not find student of id - "+id.toString());
      }
      return  theStudent;
    }

    @Override

    public void updateStudent(Student newStudentInfo) {
        Optional<Student> res  =  studentRepository.findById(newStudentInfo.getId());

        Student theStudent  = null ;

        if(res.isPresent())
            theStudent = res.get();
        else
            throw new RuntimeException("Failed to update a non exiting student of id "+newStudentInfo.getId());

        studentRepository.save(theStudent);
    }
    @Override
    public void deleteById(UUID id){
        studentRepository.deleteById(id);
    }


}
