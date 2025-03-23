package com.fitnessapp.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class WorkoutGeneralReportResponse {

    @NotNull
    private LocalDate fromMonth;

    @NotNull
    private LocalDate toMonth;

    @NotNull
    private int totalWorkouts;

    @NotNull
    private double averageDuration;

    @NotNull
    private int totalParticipants;
}
