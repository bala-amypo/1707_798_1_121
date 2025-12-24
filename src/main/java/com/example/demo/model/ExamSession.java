package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "exam_sessions")
public class ExamSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private LocalDate examDate;

    @ManyToMany
    private List<Student> students;

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
