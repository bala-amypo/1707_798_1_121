import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "seating_plans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "session_id")
    private ExamSession examSession;
    
    @ManyToOne
    @JoinColumn(name = "room_id")
    private ExamRoom room;
    
    @Column(columnDefinition = "TEXT")
    private String arrangementJson;
    
    private LocalDateTime generatedAt;
}