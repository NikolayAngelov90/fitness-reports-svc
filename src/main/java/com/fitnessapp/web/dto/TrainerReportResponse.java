package com.fitnessapp.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
public class TrainerReportResponse {

    @NotNull
    private UUID trainerId;

    @NotNull
    private LocalDate month;

    @NotNull
    private int totalWorkouts;

    @NotNull
    private double averageDuration;

    @NotNull
    private int totalParticipants;
}
