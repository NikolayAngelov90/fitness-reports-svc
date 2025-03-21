package com.fitnessapp.membership.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "membership_reports")
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDate month;

    @Column(nullable = false)
    private int activeCount;

    @Column(nullable = false)
    private int expiredCount;

    @Column(nullable = false)
    private BigDecimal totalRevenue;
}
