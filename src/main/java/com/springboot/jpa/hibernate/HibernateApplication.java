package com.springboot.jpa.hibernate;

import com.springboot.jpa.hibernate.entities.Course;
import com.springboot.jpa.hibernate.entities.Review;
import com.springboot.jpa.hibernate.repositories.CourseRepository;
import com.springboot.jpa.hibernate.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HibernateApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// studentRepository.saveStudentWithPassport();
		// courseRepository.addReviewForCourse();

		List<Review> reviews = new ArrayList<>();
		reviews.add(new Review("5", "OOSM"));
		reviews.add(new Review("4.5", "MF"));
		courseRepository.addReviewsForCourse(103L, reviews);
	}
}
