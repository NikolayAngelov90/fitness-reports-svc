package com.fitnessapp.membership.event;

import com.fitnessapp.web.dto.EventTypeRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UpsertMembershipEvent {

    @NotNull
    private LocalDate date;

    @NotNull
    private EventTypeRequest type;

    @Positive
    private BigDecimal price;
}
