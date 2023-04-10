package sphe.dev.cruddemo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sphe.dev.cruddemo.entity.Student;

import java.util.List;
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

    @Override
    public List<Student> findAll(){
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student",Student.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByName(String lastName, String firstName ){
        // Using JPA Query Language Name parameter to pass lastName and firstName Params
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=:firstParam AND firstName=:secondParam",Student.class);
        theQuery.setParameter("firstParam",lastName);
        theQuery.setParameter("secondParam",firstName);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student newStudentInfo){
        entityManager.merge(newStudentInfo);
    }

    @Override
    @Transactional
    public void delete(UUID id){
        entityManager.remove(entityManager.find(Student.class,id));
    }
}
