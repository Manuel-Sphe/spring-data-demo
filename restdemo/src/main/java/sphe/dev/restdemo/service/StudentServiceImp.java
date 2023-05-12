package sphe.dev.restdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sphe.dev.restdemo.exception.exceptions.BadRequestException;

import sphe.dev.restdemo.repository.StudentRepository;
import sphe.dev.restdemo.entity.Student;

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
    public void saveStudent( Student student) {
        Boolean existsEmail = studentRepository.selectExistsEmail(student.getEmail());
        if(existsEmail)
            throw new BadRequestException("Email "+student.getEmail()+" already exists");
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
