package com.fitnessapp.web;

import com.fitnessapp.membership.model.Membership;
import com.fitnessapp.membership.service.MembershipService;
import com.fitnessapp.web.dto.MembershipReportResponse;
import com.fitnessapp.membership.event.UpsertMembershipEvent;
import com.fitnessapp.web.mapper.DtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/membership-reports")
public class MembershipController {

    private final MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @PostMapping("/sync")
    public ResponseEntity<Void> upsertMembershipReport(@RequestBody UpsertMembershipEvent upsertMembershipEvent) {

        membershipService.upsertMembershipStats(upsertMembershipEvent);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/monthly")
    public ResponseEntity<MembershipReportResponse> getMonthlyReport(@RequestParam LocalDate month) {

        Membership membershipMonthlyReport = membershipService.getMonthlyReport(month);
        MembershipReportResponse membershipReportResponse = DtoMapper.fromMembership(membershipMonthlyReport);

        return ResponseEntity.ok(membershipReportResponse);
    }
}
