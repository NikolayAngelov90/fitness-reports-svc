package com.fitnessapp.workout.repository;

import com.fitnessapp.workout.model.WorkoutType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkoutTypeRepository extends JpaRepository<WorkoutType, UUID> {

    Optional<WorkoutType> findByWorkoutTypeAndMonth(String workoutType, LocalDate month);
}
