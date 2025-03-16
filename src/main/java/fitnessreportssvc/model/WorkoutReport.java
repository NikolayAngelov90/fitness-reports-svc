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
@Table(name = "workout_reports")
public class WorkoutReport {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDate month;

    @Column(nullable = false)
    private int totalWorkouts;

    @Column(nullable = false)
    private double averageDuration;

    @Column(nullable = false)
    private int totalParticipants;
}
