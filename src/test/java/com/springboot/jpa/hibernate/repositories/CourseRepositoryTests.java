package com.springboot.jpa.hibernate.repositories;

import com.springboot.jpa.hibernate.HibernateApplication;
import com.springboot.jpa.hibernate.entities.Course;
import com.springboot.jpa.hibernate.entities.Review;
import com.springboot.jpa.hibernate.entities.Student;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@SpringBootTest(classes = HibernateApplication.class)
class CourseRepositoryTests {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	EntityManager em;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	void findById_basic() {
		Course course = courseRepository.findById(101L);
		Assertions.assertEquals("Introduction to Astrophysics",
				course.getName());
	}

	@Test
	@DirtiesContext
	void deleteById_basic() {
		courseRepository.deleteById(101L);
		assertNull(courseRepository.findById(101L));
	}

	@Test
	@DirtiesContext
	void save_basic() {
		// get a course & update details & save
		Course course = courseRepository.findById(101L);
		assertEquals("Introduction to Astrophysics", course.getName());

		course.setName("Introduction to Astrophysics by N.D Tysson");

		courseRepository.save(course);

		Course course1 = courseRepository.findById(101L);
		assertEquals("Introduction to Astrophysics by N.D Tysson", course1.getName());
	}

	@Test
	@DirtiesContext
	void entityManagerPlayground() {
		courseRepository.entityManagerPlayground();
	}

	@Test
	void JPQL() {
		// courseRepository.JPQL_basics();
		courseRepository.JPQAL_basics_typed();
	}

	@Test
	void JPQL_where() {
		courseRepository.jpql_where();
	}

	@Test
	void namedQuery() {
		courseRepository.namedQuery();
	}

	@Test
	void nativeQuery() {
		courseRepository.nativeQueryBasic();
	}

	@Test
	@Transactional
	public void getReviewsForCourse() {
		Course course1 = courseRepository.findById(101L);
		logger.info("Reviews are => {}", course1.getReviews());
	}

	@Test
	@Transactional
	public void getReview() {
		Review review = em.find(Review.class, 5001L);
		logger.info("Reviews are => {}", review.getCourse());
	}

	@Test
	public void jpql_courses_without_students() {
		TypedQuery<Course> query = em.createQuery(
				"Select c from Course c where c.students is empty",
				Course.class
		);
		List<Course> courses = query.getResultList();
		logger.info("Courses with no students enrolled in => {}", courses);
	}

	@Test
	public void jpql_courses_with_atleast_two_students() {
		TypedQuery<Course> query = em.createQuery(
				"Select c from Course c where size(c.students) >= 2",
				Course.class
		);
		List<Course> courses = query.getResultList();
		logger.info("Courses with at least 2 students enrolled in => {}", courses);
	}

	@Test
	public void jpql_courses_ordered_by_students() {
		TypedQuery<Course> query = em.createQuery(
				"Select c from Course c order by size(c.students)",
				Course.class
		);
		List<Course> courses = query.getResultList();
		logger.info("Courses ordered by students enrolled in ASC => {}", courses);
	}

	@Test
	public void jpql_students_passport_like() {
		TypedQuery<Student> query = em.createQuery(
				"Select s from Student s where s.passport.number like '%1234%'",
				Student.class
		);
		List<Student> students = query.getResultList();
		logger.info("Students with passport like %1234% => {}", students);
	}

}
