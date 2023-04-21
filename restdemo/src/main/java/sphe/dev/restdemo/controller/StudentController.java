package sphe.dev.restdemo.controller;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import sphe.dev.restdemo.dao.StudentDAO;
import sphe.dev.restdemo.entity.Student;
import sphe.dev.restdemo.service.StudentService;


@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("students")
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> students = studentService.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("students/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable UUID studentId){
        Student student = studentService.findById(studentId);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @PostMapping("students")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        // Just incase they pass an id in JSON ... set id to null
        student.setId(null);
        studentService.saveStudent(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("students/")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){

        studentService.update(student);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @DeleteMapping("students/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable UUID studentId){
        studentService.delete(studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
