package fitnessreportssvc.repository;

import fitnessreportssvc.model.WorkoutTypeReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkoutTypeRepository extends JpaRepository<WorkoutTypeReport, UUID> {

    Optional<WorkoutTypeReport> findByWorkoutTypeAndMonth(String workoutType, LocalDate month);
}
