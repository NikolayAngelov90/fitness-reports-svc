package com.fitnessapp.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class WorkoutTypeReportResponse {

    @NotNull
    private String workoutType;

    @NotNull
    private LocalDate month;

    @NotNull
    private int totalWorkouts;

    @NotNull
    private double averageDuration;

    @NotNull
    private int totalParticipants;
}
