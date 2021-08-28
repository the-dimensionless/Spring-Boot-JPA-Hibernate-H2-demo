package com.springboot.jpa.hibernate.repositories;

import com.springboot.jpa.hibernate.entities.Employee;
import com.springboot.jpa.hibernate.entities.EmployeeFullTime;
import com.springboot.jpa.hibernate.entities.EmployeePartTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    // insert an employee
    public void insert(Employee employee) {
        em.persist(employee);
    }

    // get all employees
//    public List<Employee> getAllEmployees() {
//        return em.createQuery("select e from Employee e", Employee.class).getResultList();
//    }

    public List<EmployeePartTime> getPartTimeEmployees() {
        return em.createQuery("select e from EmployeePartTime e", EmployeePartTime.class).getResultList();
    }

    public List<EmployeePartTime> getFullTimeEmployees() {
        return em.createQuery("select e from EmployeePartTime e", EmployeePartTime.class).getResultList();
    }
}
