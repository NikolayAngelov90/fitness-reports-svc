package com.fitnessapp.membership.service;

import com.fitnessapp.exceptions.ReportDataException;
import com.fitnessapp.membership.model.Membership;
import com.fitnessapp.membership.repository.MembershipRepository;
import com.fitnessapp.web.dto.EventTypeRequest;
import com.fitnessapp.membership.event.UpsertMembershipEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
@Service
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public MembershipService(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @KafkaListener(topics = "membership-events")
    public void upsertMembershipStats(UpsertMembershipEvent event) {
        log.info("Got Message from membership-events topic {}", event);

        LocalDate month = event.getDate().withDayOfMonth(1);
        Membership report = membershipRepository.findByMonth(month)
                .orElse(Membership.builder()
                        .month(month)
                        .totalRevenue(BigDecimal.ZERO)
                        .build());

        if (event.getType() == EventTypeRequest.ACTIVE) {
            report.setActiveCount(report.getActiveCount() + 1);
            report.setTotalRevenue(report.getTotalRevenue().add(event.getPrice()));
        } else if (event.getType() == EventTypeRequest.EXPIRED) {
            report.setActiveCount(report.getActiveCount() - 1);
            report.setExpiredCount(report.getExpiredCount() + 1);
        }

        membershipRepository.save(report);
        log.info("Membership Stats updated successfully");
    }

    public Membership getMonthlyReport(LocalDate month) {

        LocalDate normalizedMonth = month.withDayOfMonth(1);
        return membershipRepository.findByMonth(normalizedMonth).orElseThrow(
                () -> new ReportDataException("Report not found for month: " + normalizedMonth));
    }
}
