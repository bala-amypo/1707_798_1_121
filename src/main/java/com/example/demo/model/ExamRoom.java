import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "exam_rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String roomNumber;
    
    private Integer rows;
    private Integer columns;
    private Integer capacity;
    
    @PrePersist
    @PreUpdate
    public void ensureCapacityMatches() {
        if (rows != null && columns != null) {
            capacity = rows * columns;
        } else if (capacity == null) {
            capacity = 1;
        }
    }
}