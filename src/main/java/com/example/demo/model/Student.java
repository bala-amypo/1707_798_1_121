package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String rollNumber;
    
    private String name;
    private String department;
    private Integer year;
    
    // Constructors
    public Student() {}
    
    public Student(Long id, String rollNumber, String name, String department, Integer year) {
        this.id = id;
        this.rollNumber = rollNumber;
        this.name = name;
        this.department = department;
        this.year = year;
    }
    
    // Getters
    public Long getId() { return id; }
    public String getRollNumber() { return rollNumber; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public Integer getYear() { return year; }
    
    // Setters
    public void setId(Long id) { this.id = id; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setYear(Integer year) { this.year = year; }
    
    // Builder pattern
    public static StudentBuilder builder() {
        return new StudentBuilder();
    }
    
    public static class StudentBuilder {
        private Long id;
        private String rollNumber;
        private String name;
        private String department;
        private Integer year;
        
        public StudentBuilder id(Long id) { this.id = id; return this; }
        public StudentBuilder rollNumber(String rollNumber) { this.rollNumber = rollNumber; return this; }
        public StudentBuilder name(String name) { this.name = name; return this; }
        public StudentBuilder department(String department) { this.department = department; return this; }
        public StudentBuilder year(Integer year) { this.year = year; return this; }
        
        public Student build() {
            return new Student(id, rollNumber, name, department, year);
        }
    }
}