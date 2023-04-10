package sphe.dev.cruddemo.dao;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sphe.dev.cruddemo.entity.Student;

import java.util.UUID;

@Repository
public class StudentDAOImp implements StudentDAO {
    // define field for entity manager
    private final EntityManager entityManager;

    public StudentDAOImp(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    // inject the entity manager, using constructor injection

    // implement the save method
    @Override
    @Transactional
    public void save(Student student){
        entityManager.persist(student);
    }

    @Override
    public Student findById(UUID id){
        return entityManager.find(Student.class,id);
    }
}
