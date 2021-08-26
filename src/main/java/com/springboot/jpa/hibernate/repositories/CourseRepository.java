package com.springboot.jpa.hibernate.repositories;

import com.springboot.jpa.hibernate.entities.Course;
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

    public void JPQL_basics() {
        List resultList = em.createQuery("Select c from Course c").getResultList();
        logger.info("Select c from Course c" + resultList);
    }

    public void JPQAL_basics_typed() {
        TypedQuery<Course> query = em.createQuery("Select c from Course c", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c from Course c with typed query" + resultList);
    }

    public void jpql_where() {
        TypedQuery<Course> query = em.createQuery("Select c from Course c where name like '%physics'", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c from Course c where name like '%Astro' with typed query = {}" + resultList);
    }

    public void namedQuery() {
        Query query = em.createNamedQuery("query_get_all_courses");
        List resultList = query.getResultList();
        logger.info("Named Query Result : {}" + resultList);
    }

    public void nativeQueryBasic() {
        Query query = em.createNativeQuery("select * from course", Course.class);
        List resultList = query.getResultList();
        logger.info("Native Query Result : {}" + resultList);
    }
}
