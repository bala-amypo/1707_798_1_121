package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "exam_sessions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
