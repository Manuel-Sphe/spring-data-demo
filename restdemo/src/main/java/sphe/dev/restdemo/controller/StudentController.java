package sphe.dev.restdemo.controller;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student){
        studentService.saveStudent(student);
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }


    @PutMapping("students/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable UUID studentId, @RequestBody Student student){
        Student std = studentService.findById(studentId);
        std.setFirstName(student.getFirstName());
        std.setLastName(student.getLastName());
        std.setEmail(student.getEmail());
        studentService.updateStudent(std);
        return new ResponseEntity<>(std,HttpStatus.OK);

    }

    @DeleteMapping("students/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable UUID studentId){
        studentService.delete(studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
