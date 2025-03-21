package com.fitnessapp.workout.repository;

import com.fitnessapp.workout.model.WorkoutTrainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkoutTrainerRepository extends JpaRepository<WorkoutTrainer, UUID> {

    Optional<WorkoutTrainer> findByTrainerIdAndMonth(UUID trainerId, LocalDate month);
}
