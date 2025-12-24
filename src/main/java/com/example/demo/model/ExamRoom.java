// ExamRoom.java
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exam_rooms")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "room_number", nullable = false, unique = true)
    private String roomNumber;
    
    @Column(name = "room_rows")  // Change from "rows" to "room_rows"
    private Integer rows;
    
    @Column(name = "room_columns")  // Change from "columns" to "room_columns"
    private Integer columns;
    
    private Integer capacity;
    
    @PrePersist
    @PreUpdate
    public void ensureCapacityMatches() {
        if (rows != null && columns != null) {
            this.capacity = rows * columns;
        } else if (capacity == null) {
            this.capacity = 1;
        }
    }
}