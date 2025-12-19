package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    name = "exam_sessions",
    uniqueConstraints = @UniqueConstraint(columnNames = "courseCode")
)
public class ExamSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String courseCode;

    private LocalDate examDate;

    private String examTime;

    @ManyToMany
    @JoinTable(
        name = "exam_session_students",
        joinColumns = @JoinColumn(name = "exam_session_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students = new HashSet<>();

    // ---------- Constructors ----------
    public ExamSession() {}

    public ExamSession(String courseCode, LocalDate examDate, String examTime) {
        this.courseCode = courseCode;
        this.examDate = examDate;
        this.examTime = examTime;
    }

    // ---------- Getters & Setters ----------
    public Long getId() { return id; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public LocalDate getExamDate() { return examDate; }
    public void setExamDate(LocalDate examDate) { this.examDate = examDate; }

    public String getExamTime() { return examTime; }
    public void setExamTime(String examTime) { this.examTime = examTime; }

    public Set<Student> getStudents() { return students; }
    public void setStudents(Set<Student> students) { this.students = students; }
}
