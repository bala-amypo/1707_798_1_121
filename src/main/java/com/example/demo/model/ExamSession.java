// ExamSession.java
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "exam_sessions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "course_code", nullable = false)
    private String courseCode;
    
    @Column(name = "exam_date", nullable = false)
    private LocalDate examDate;
    
    @Column(name = "exam_time", nullable = false)
    private String examTime;
    
    @ManyToMany
    @JoinTable(
        name = "session_students",
        joinColumns = @JoinColumn(name = "session_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @Builder.Default
    private Set<Student> students = new HashSet<>();
}