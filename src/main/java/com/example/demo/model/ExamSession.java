package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "exam_sessions")
public class ExamSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String courseCode;
    private LocalDate examDate;
    private String examTime;
    
    @ManyToMany
    @JoinTable(
        name = "session_students",
        joinColumns = @JoinColumn(name = "session_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students = new HashSet<>();
    
    // Default constructor
    public ExamSession() {}
    
    // Constructor with all fields
    public ExamSession(Long id, String courseCode, LocalDate examDate, String examTime, Set<Student> students) {
        this.id = id;
        this.courseCode = courseCode;
        this.examDate = examDate;
        this.examTime = examTime;
        this.students = students != null ? students : new HashSet<>();
    }
    
    // Getters
    public Long getId() { return id; }
    public String getCourseCode() { return courseCode; }
    public LocalDate getExamDate() { return examDate; }
    public String getExamTime() { return examTime; }
    public Set<Student> getStudents() { return students; }
    
    // Setters
    public void setId(Long id) { this.id = id; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public void setExamDate(LocalDate examDate) { this.examDate = examDate; }
    public void setExamTime(String examTime) { this.examTime = examTime; }
    public void setStudents(Set<Student> students) { 
        this.students = students != null ? students : new HashSet<>();
    }
    
    // Builder pattern
    public static ExamSessionBuilder builder() {
        return new ExamSessionBuilder();
    }
    
    public static class ExamSessionBuilder {
        private Long id;
        private String courseCode;
        private LocalDate examDate;
        private String examTime;
        private Set<Student> students;
        
        public ExamSessionBuilder id(Long id) { 
            this.id = id; 
            return this; 
        }
        
        public ExamSessionBuilder courseCode(String courseCode) { 
            this.courseCode = courseCode; 
            return this; 
        }
        
        public ExamSessionBuilder examDate(LocalDate examDate) { 
            this.examDate = examDate; 
            return this; 
        }
        
        public ExamSessionBuilder examTime(String examTime) { 
            this.examTime = examTime; 
            return this; 
        }
        
        public ExamSessionBuilder students(Set<Student> students) { 
            this.students = students; 
            return this; 
        }
        
        public ExamSession build() {
            return new ExamSession(id, courseCode, examDate, examTime, students);
        }
    }
}