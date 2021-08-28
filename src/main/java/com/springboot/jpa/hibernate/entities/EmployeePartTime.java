package com.springboot.jpa.hibernate.entities;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class EmployeePartTime extends Employee {
    protected EmployeePartTime() {}

    public EmployeePartTime(String name, BigDecimal hourlyWage) {
        super(name);
        this.hourlyWage = hourlyWage;
    }

    private BigDecimal hourlyWage;
}
