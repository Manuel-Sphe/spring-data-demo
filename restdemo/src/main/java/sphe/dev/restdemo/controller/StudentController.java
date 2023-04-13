package sphe.dev.restdemo.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import sphe.dev.restdemo.entity.Student;
import sphe.dev.restdemo.exception.StudentErrorResponse;
import sphe.dev.restdemo.exception.StudentNotFoundException;

@RestController
@RequestMapping("/student")
public class StudentController {
  
    public List<Student> students ;
    public HashMap<UUID,Student> allData = new HashMap<>();
    // run this first .
    @PostConstruct
    public void LoadData(){
        this.students = new ArrayList<>();


        students.add(new Student(UUID.randomUUID(),"Sphe","Mnisi","sphemns@uwc.ac.za"));
        students.add(new Student(UUID.randomUUID(), "Noluthando", "Jiyane", "noluthandogiyane@mut.ac.za"));
        students.add(new Student(UUID.randomUUID(), "Ntokozo", "Femeni", "ntokofem@gamil.com"));

        students.forEach((student)->{
            allData.put(student.getId(),student);
        });
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("all")
    public ResponseEntity<List<Student>> getAllStudent(){
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable UUID studentId){
        Student student = allData.get(studentId);
        if(student==null)
          throw new StudentNotFoundException("Student id not found - "+studentId.toString());
        return new ResponseEntity<>(student,HttpStatus.OK);
    }
    
}
