package sphe.dev.restdemo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import sphe.dev.restdemo.entity.Student;
import sphe.dev.restdemo.exception.exceptions.StudentNotFoundException;
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
<<<<<<< HEAD
        // Just incase they pass an id in JSON ... set id to null
        student.setId(null);
        studentService.saveStudent(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("students/")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){

        studentService.update(student);
        return new ResponseEntity<>(student,HttpStatus.OK);
=======
        studentService.save(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("students/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable UUID studentId, @RequestBody Student student){
        Student std = studentService.findById(studentId);
        if(std==null)
            throw new StudentNotFoundException("Can't update info of a non-existing student with that Id");
        std.setFirstName(student.getFirstName());
        std.setLastName(student.getLastName());
        std.setEmail(student.getEmail());
        studentService.updateStudent(std);
        return new ResponseEntity<>(std,HttpStatus.OK);
>>>>>>> bdec945 (JpaRepository)
    }

    @DeleteMapping("students/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable UUID studentId){
        Student tempStudent = studentService.findById(studentId);
        if(tempStudent == null)
            throw new StudentNotFoundException("Sorry a student with that student is found");
        studentService.deleteById(studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
