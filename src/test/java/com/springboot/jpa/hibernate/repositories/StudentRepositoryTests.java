package com.springboot.jpa.hibernate.repositories;

import com.springboot.jpa.hibernate.HibernateApplication;
import com.springboot.jpa.hibernate.entities.Passport;
import com.springboot.jpa.hibernate.entities.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest(classes = HibernateApplication.class)
public class StudentRepositoryTests {
    @Autowired
    EntityManager em;

    @Autowired
    StudentRepository studentRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    @Transactional
    public void getStudentWithPassportDetails() {
        Student student  = em.find(Student.class, 2001L);
        logger.info("Student => {}", student);
        //logger.info("Passport details => {}", student.getPassport());
    }

    @Test
    @Transactional
    public void getPassportWithStudentDetails() {
        Passport passport = em.find(Passport.class, 4001L);
        logger.info("Passport => {}", passport);
        logger.info("Student => {}", passport.getStudent());
    }

    @Test
    @Transactional
    public void someTests() {
        this.studentRepository.updating();
    }

    @Test
    @Transactional
    public void getStudentsAndCourses() {
        Student student = em.find(Student.class, 2001L);
        logger.info("Student => {}", student);
        logger.info("Course => {}", student.getCourses());
    }
}
