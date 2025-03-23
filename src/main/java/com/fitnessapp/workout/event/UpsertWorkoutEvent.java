package com.fitnessapp.workout.event;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UpsertWorkoutEvent {

    @NotNull
    String workoutType;

    @NotNull
    LocalDate startTime;

    @NotNull
    int duration;

    @NotNull
    int participants;

    @NotNull
    private UUID trainerId;
}
