package com.springboot.jpa.hibernate.entities;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class EmployeeFullTime extends Employee {
        protected EmployeeFullTime() {}

        public EmployeeFullTime(String name, BigDecimal salary) {
            super(name);
            this.salary = salary;
        }

        private BigDecimal salary;

}
