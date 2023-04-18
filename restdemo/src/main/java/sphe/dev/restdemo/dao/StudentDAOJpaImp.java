package sphe.dev.restdemo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sphe.dev.restdemo.entity.Student;

import java.util.List;
import java.util.UUID;

@Repository
public class StudentDAOJpaImp implements  StudentDAO{

    private  final EntityManager entityManager;

    @Autowired
    public StudentDAOJpaImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void saveStudent(Student student) {
        entityManager.persist(student);
    }

    @Override
    public List<Student> findAll(){
        TypedQuery<Student> query = entityManager.createQuery("from Student",Student.class);
        return query.getResultList();
    }

    @Override
    public Student findById(UUID id){
        return entityManager.find(Student.class,id);
    }

    @Override
    @Transactional
    public void update(Student newStudentInfo){
        entityManager.merge(newStudentInfo);

    }

    @Override
    @Transactional
    public void delete(UUID id){
        Student student = entityManager.find(Student.class,id);
        entityManager.remove(student);
    }


}
