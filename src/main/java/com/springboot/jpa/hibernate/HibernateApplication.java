package com.springboot.jpa.hibernate;

import com.springboot.jpa.hibernate.entities.Course;
import com.springboot.jpa.hibernate.repositories.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HibernateApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Course course = courseRepository.findById(101L);

		logger.info("Course 101 is => " + course);

		// courseRepository.deleteById(101L);

		courseRepository.save(new Course("Introduction to Microservices"));
	}
}
