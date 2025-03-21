package com.fitnessapp.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
public class MembershipReportResponse {

    @NotNull
    private LocalDate month;

    @NotNull
    private int activeCount;

    @NotNull
    private int expiredCount;

    @NotNull
    private BigDecimal totalRevenue;
}
