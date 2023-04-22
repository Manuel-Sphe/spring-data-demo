package sphe.dev.restdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sphe.dev.restdemo.entity.Student;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student,UUID> {
}
