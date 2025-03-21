package com.fitnessapp.workout.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class WorkoutStats {

    @Column(nullable = false)
    private int totalWorkouts;

    @Column(nullable = false)
    private double averageDuration;

    @Column(nullable = false)
    private int totalParticipants;
}
