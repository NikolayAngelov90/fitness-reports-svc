package com.fitnessapp.web.mapper;

import com.fitnessapp.membership.model.Membership;
import com.fitnessapp.web.dto.MembershipReportResponse;
import com.fitnessapp.web.dto.TrainerReportResponse;
import com.fitnessapp.web.dto.WorkoutGeneralReportResponse;
import com.fitnessapp.web.dto.WorkoutTypeReportResponse;
import com.fitnessapp.workout.model.WorkoutGeneral;
import com.fitnessapp.workout.model.WorkoutTrainer;
import com.fitnessapp.workout.model.WorkoutType;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class DtoMapper {

    public static WorkoutTypeReportResponse fromWorkoutType(WorkoutType entity) {
        return WorkoutTypeReportResponse.builder()
                .type(entity.getWorkoutType())
                .month(entity.getMonth())
                .totalWorkouts(entity.getStats().getTotalWorkouts())
                .averageDuration(entity.getStats().getAverageDuration())
                .totalParticipants(entity.getStats().getTotalParticipants())
                .build();
    }

    public static TrainerReportResponse fromWorkoutTrainer(WorkoutTrainer entity) {
        return TrainerReportResponse.builder()
                .trainerId(entity.getTrainerId())
                .month(entity.getMonth())
                .totalWorkouts(entity.getStats().getTotalWorkouts())
                .averageDuration(entity.getStats().getAverageDuration())
                .totalParticipants(entity.getStats().getTotalParticipants())
                .build();
    }

    public static WorkoutGeneralReportResponse fromWorkoutGeneral(WorkoutGeneral entity,
                                                                  LocalDate fromMonth,
                                                                  LocalDate toMonth) {
        return WorkoutGeneralReportResponse.builder()
                .fromMonth(fromMonth)
                .toMonth(toMonth)
                .totalWorkouts(entity.getStats().getTotalWorkouts())
                .averageDuration(entity.getStats().getAverageDuration())
                .totalParticipants(entity.getStats().getTotalParticipants())
                .build();
    }

    public static MembershipReportResponse fromMembership(Membership entity) {

        return MembershipReportResponse.builder()
                .month(entity.getMonth())
                .activeCount(entity.getActiveCount())
                .expiredCount(entity.getExpiredCount())
                .totalRevenue(entity.getTotalRevenue())
                .build();
    }
}
