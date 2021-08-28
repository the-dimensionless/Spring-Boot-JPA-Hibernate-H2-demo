package com.springboot.jpa.hibernate.repositories;

import com.springboot.jpa.hibernate.HibernateApplication;
import com.springboot.jpa.hibernate.entities.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest(classes = HibernateApplication.class)
public class StudentRepositoryTests {
    @Autowired
    EntityManager em;

    @Autowired
    StudentRepository studentRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void getStudentWithPassportDetails() {
        Student student  = em.find(Student.class, 2001L);
        logger.info("Student => {}", student);
        logger.info("Passport details => {}", student.getPassport());
    }
}
