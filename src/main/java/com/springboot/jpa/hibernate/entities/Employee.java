package com.springboot.jpa.hibernate.entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class Employee {

    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false)
    private String name;

    protected Employee() {

    }

    public Employee(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
