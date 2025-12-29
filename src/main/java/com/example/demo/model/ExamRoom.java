package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "exam_rooms",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "roomNumber")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String roomNumber;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer rows;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer columns;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer capacity;

    

    public void ensureCapacityMatches() {
        if (rows != null && columns != null) {
            this.capacity = rows * columns;
        }
    }

    
    @PrePersist
    @PreUpdate
    public void syncCapacity() {
        ensureCapacityMatches();
    }
}