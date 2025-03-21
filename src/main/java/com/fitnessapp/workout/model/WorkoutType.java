package com.fitnessapp.workout.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "workout_type_reports")
public class WorkoutType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDate month;

    @Embedded
    private WorkoutStats stats;

    @Column(nullable = false)
    private String workoutType;
}
