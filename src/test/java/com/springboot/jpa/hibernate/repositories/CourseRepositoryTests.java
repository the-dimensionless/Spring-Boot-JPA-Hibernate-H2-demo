package com.springboot.jpa.hibernate.repositories;

import com.springboot.jpa.hibernate.HibernateApplication;
import com.springboot.jpa.hibernate.entities.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = HibernateApplication.class)
class CourseRepositoryTests {

	@Autowired
	CourseRepository courseRepository;

	@Test
	void findById_basic() {
		Course course = courseRepository.findById(101L);
		Assertions.assertEquals("Introduction to Astrophysics",
				course.getName());
	}

}
