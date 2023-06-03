package sphe.dev.restdemo.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sphe.dev.restdemo.exception.exceptions.BadRequestException;

import sphe.dev.restdemo.exception.exceptions.StudentNotFoundException;
import sphe.dev.restdemo.repository.StudentRepository;
import sphe.dev.restdemo.entity.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;

@Service
public class StudentServiceImp implements StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentServiceImp.class);
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
      return results.orElseThrow(() -> new StudentNotFoundException("Can't find student with that Id"));

    }

    @Override
    public void updateStudent(Student newStudentInfo) {
        logger.debug("REST request to update student info : {} ",newStudentInfo);
        studentRepository.findById(newStudentInfo.getId())
                .ifPresentOrElse(student -> {
                    student.setFirstName(newStudentInfo.getFirstName());
                    student.setLastName(newStudentInfo.getLastName());
                    student.setEmail(newStudentInfo.getEmail());
                    studentRepository.save(student);
                }, () -> {
                            throw new StudentNotFoundException("Can't update info of a non-existing student with that Id");}
                );
    }
    @Override
    public void delete(UUID id){
        studentRepository.findById(id)
                .ifPresentOrElse(student -> {
                        studentRepository.deleteById(id);
                            },
                        () -> {
                            throw new StudentNotFoundException("Can't delete a non-existing student with that ID");
                        });

    }


}
