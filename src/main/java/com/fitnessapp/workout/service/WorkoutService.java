package com.fitnessapp.workout.service;

import com.fitnessapp.exceptions.ReportDataException;
import com.fitnessapp.workout.model.WorkoutGeneral;
import com.fitnessapp.workout.model.WorkoutStats;
import com.fitnessapp.workout.model.WorkoutTrainer;
import com.fitnessapp.workout.model.WorkoutType;
import com.fitnessapp.workout.repository.WorkoutGeneralRepository;
import com.fitnessapp.workout.repository.WorkoutTrainerRepository;
import com.fitnessapp.workout.repository.WorkoutTypeRepository;
import com.fitnessapp.workout.event.UpsertWorkoutEvent;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class WorkoutService {

    private final WorkoutTypeRepository workoutTypeRepository;
    private final WorkoutTrainerRepository workoutTrainerRepository;
    private final WorkoutGeneralRepository workoutGeneralRepository;


    public WorkoutService(WorkoutTypeRepository workoutTypeRepository,
                          WorkoutTrainerRepository workoutTrainerRepository,
                          WorkoutGeneralRepository workoutGeneralRepository) {
        this.workoutTypeRepository = workoutTypeRepository;
        this.workoutTrainerRepository = workoutTrainerRepository;
        this.workoutGeneralRepository = workoutGeneralRepository;
    }

    @KafkaListener(topics = "workout-events")
    @Transactional
    public void upsertWorkoutStats(UpsertWorkoutEvent event) {

        WorkoutType typeReport = workoutTypeRepository
                .findByWorkoutTypeAndMonth(event.getWorkoutType(), event.getStartTime().withDayOfMonth(1))
                .orElse(WorkoutType.builder()
                        .workoutType(event.getWorkoutType())
                        .month(event.getStartTime().withDayOfMonth(1))
                        .stats(WorkoutStats.builder().build())
                        .build());

        updateStats(event, typeReport.getStats());
        workoutTypeRepository.save(typeReport);

        WorkoutTrainer trainerReport = workoutTrainerRepository.findByTrainerIdAndMonth(event.getTrainerId(), event.getStartTime().withDayOfMonth(1))
                .orElse(WorkoutTrainer.builder()
                        .trainerId(event.getTrainerId())
                        .month(event.getStartTime().withDayOfMonth(1))
                        .stats(WorkoutStats.builder().build())
                        .build());

        updateStats(event, trainerReport.getStats());
        workoutTrainerRepository.save(trainerReport);

        WorkoutGeneral generalReport = workoutGeneralRepository.findByMonth(event.getStartTime().withDayOfMonth(1))
                .orElse(WorkoutGeneral.builder()
                        .month(event.getStartTime().withDayOfMonth(1))
                        .stats(WorkoutStats.builder().build())
                        .build());

        updateStats(event, generalReport.getStats());
        workoutGeneralRepository.save(generalReport);
        log.info("Workout stats updated successfully");
    }

    public WorkoutType getWorkoutTypeReport(String workoutType, LocalDate month) {
        return workoutTypeRepository.findByWorkoutTypeAndMonth(workoutType, month)
                .orElseThrow(() -> new ReportDataException("Report not found"));
    }

    public WorkoutTrainer getTrainerReport(UUID trainerId, LocalDate month) {
        return workoutTrainerRepository.findByTrainerIdAndMonth(trainerId, month)
                .orElseThrow(() -> new ReportDataException("Report not found"));
    }

    public WorkoutGeneral getWorkoutGeneralReport(LocalDate fromMonth, LocalDate toMonth) {

        List<WorkoutGeneral> allWorkoutsByPeriod = workoutGeneralRepository.findByMonthBetween(
                fromMonth.withDayOfMonth(1), toMonth.withDayOfMonth(1));
        if (allWorkoutsByPeriod.isEmpty()) {
            throw new ReportDataException("Report not found");
        }

        int totalWorkouts = allWorkoutsByPeriod
                .stream()
                .mapToInt(w -> w.getStats().getTotalWorkouts())
                .sum();

        double averageDuration = allWorkoutsByPeriod
                .stream()
                .mapToDouble(w -> w.getStats().getAverageDuration())
                .average()
                .orElse(0);

        int totalParticipants = allWorkoutsByPeriod
                .stream()
                .mapToInt(w -> w.getStats().getTotalParticipants())
                .sum();

        WorkoutStats workoutStats = WorkoutStats.builder()
                .totalWorkouts(totalWorkouts)
                .averageDuration(averageDuration)
                .totalParticipants(totalParticipants)
                .build();

        return WorkoutGeneral.builder()
                .month(fromMonth)
                .stats(workoutStats)
                .build();
    }

    private void updateStats(UpsertWorkoutEvent dto, WorkoutStats aggregate) {
        aggregate.setTotalWorkouts(aggregate.getTotalWorkouts() + 1);
        aggregate.setTotalParticipants(aggregate.getTotalParticipants() + dto.getParticipants());
        aggregate.setAverageDuration((aggregate.getAverageDuration() *
                (aggregate.getTotalWorkouts() - 1) + dto.getDuration()) / aggregate.getTotalWorkouts());
    }
}
