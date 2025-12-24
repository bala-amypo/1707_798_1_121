// SeatingPlan.java
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "seating_plans")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private ExamSession examSession;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private ExamRoom room;
    
    @Column(name = "arrangement_json", columnDefinition = "TEXT")
    private String arrangementJson;
    
    @Column(name = "generated_at")
    private LocalDateTime generatedAt;
}