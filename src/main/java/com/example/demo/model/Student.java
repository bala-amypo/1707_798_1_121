package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "students",
    uniqueConstraints = @UniqueConstraint(columnNames = "rollNumber")
)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String rollNumber;

    private String name;

    private String department;

    private Integer year;

    // ---------- Constructors ----------
    public Student() {}

    public Student(String rollNumber, String name, String department, Integer year) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.department = department;
        this.year = year;
    }

    // ---------- Getters & Setters ----------
    public Long getId() { return id; }

    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
}
