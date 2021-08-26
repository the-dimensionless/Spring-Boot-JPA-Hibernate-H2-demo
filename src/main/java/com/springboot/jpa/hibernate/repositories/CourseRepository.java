package com.springboot.jpa.hibernate.repositories;

import com.springboot.jpa.hibernate.entities.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public Course save (Course course) {
        if (course.getId() == null) {
            // insert operation
            em.persist(course);
        } else {
            // update
            em.merge(course);
        }
        return course;
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        em.remove(course);
    }

    public void entityManagerPlayground() {
        logger.info("Start: em playground");

        // create new course
        Course course = new Course("REST API");
        // persist
        em.persist(course);
        // update
        course.setName("REST API Updated!");

    }
}
