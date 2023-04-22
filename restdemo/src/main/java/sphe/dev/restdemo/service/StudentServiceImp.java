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
<<<<<<< HEAD
    @Transactional
    public void saveStudent(Student student) {
        studentDAO.saveStudent(student);
=======
    public void save(Student student) {
        studentRepository.save(student);
>>>>>>> bdec945 (JpaRepository)
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
<<<<<<< HEAD
    @Transactional
    public void update(Student newStudentInfo){
        studentDAO.update(newStudentInfo);
    }
=======
    public void updateStudent(Student newStudentInfo) {
        Optional<Student> res  =  studentRepository.findById(newStudentInfo.getId());

        Student theStudent  = null ;
>>>>>>> bdec945 (JpaRepository)

        if(res.isPresent())
            theStudent = res.get();
        else
            throw new RuntimeException("Failed to update a non exiting student of id "+newStudentInfo.getId());

        studentRepository.save(theStudent);
    }
    @Override
<<<<<<< HEAD
    @Transactional
    public void delete(UUID id){
        studentDAO.delete(id);
=======
    public void deleteById(UUID id){
        studentRepository.deleteById(id);
>>>>>>> bdec945 (JpaRepository)
    }


}
