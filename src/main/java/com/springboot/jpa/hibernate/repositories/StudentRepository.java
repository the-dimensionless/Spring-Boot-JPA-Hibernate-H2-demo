package com.springboot.jpa.hibernate.repositories;

import com.springboot.jpa.hibernate.entities.Course;
import com.springboot.jpa.hibernate.entities.Passport;
import com.springboot.jpa.hibernate.entities.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class StudentRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public Student save (Student student) {
        if (student.getId() == null) {
            // insert operation
            em.persist(student);
        } else {
            // update
            em.merge(student);
        }
        return student;
    }

    public void saveStudentWithPassport() {
        Passport passport =  new Passport("Z123456");
        em.persist(passport); // if not persisted, we get Transient Object exception (passport being referred without creation

        Student student = new Student("Sumit"); //owning side of relation
        student.setPassport(passport);

        em.persist(student);
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        em.remove(student);
    }

    @Transactional
    public void updating() {
        // DB OP 1 -> Retrieve Student
        Student student = em.find(Student.class, 2001L);
        // Persistence Context (Student)

        // DB OP 2 -> Retrieve Passport
        Passport passport = student.getPassport();
        // Persistence Context (Student, Passport)

        // DB OP 3 -> Update Passport
        passport.setNumber("N123456");
        // Persistence Context (Student, Passport++)

        // DB OP 4 -> Update Student
        student.setName("Updated Name");
        // Persistence Context (Student++, Passport++)

        // Without @Transactional, each DB query has it's own Persistence Context and closes session after performing it!
    }

}
