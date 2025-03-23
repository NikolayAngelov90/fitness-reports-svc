package com.fitnessapp.workout.repository;

import com.fitnessapp.workout.model.WorkoutGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkoutGeneralRepository extends JpaRepository<WorkoutGeneral, UUID> {


    Optional<WorkoutGeneral> findByMonth(LocalDate month);

    List<WorkoutGeneral> findByMonthBetween(LocalDate fromMonth, LocalDate toMonth);
}
