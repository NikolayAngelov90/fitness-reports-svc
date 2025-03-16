package fitnessreportssvc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trainer_reports")
public class TrainerReport {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private UUID trainerId;

    @Column(nullable = false)
    private LocalDate month;

    @Column(nullable = false)
    private int totalWorkouts;

    @Column(nullable = false)
    private double averageDuration;

    @Column(nullable = false)
    private String mostCommonWorkout;
}
