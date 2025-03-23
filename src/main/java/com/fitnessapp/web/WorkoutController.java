package com.fitnessapp.web;

import com.fitnessapp.web.dto.TrainerReportResponse;
import com.fitnessapp.web.dto.WorkoutGeneralReportResponse;
import com.fitnessapp.web.dto.WorkoutTypeReportResponse;
import com.fitnessapp.web.mapper.DtoMapper;
import com.fitnessapp.workout.model.WorkoutGeneral;
import com.fitnessapp.workout.model.WorkoutTrainer;
import com.fitnessapp.workout.model.WorkoutType;
import com.fitnessapp.workout.service.WorkoutService;
import com.fitnessapp.workout.event.UpsertWorkoutEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/workout-reports")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/sync")
    public ResponseEntity<Void> upsertWorkoutReport(@RequestBody UpsertWorkoutEvent upsertWorkoutEvent) {

        workoutService.upsertWorkoutStats(upsertWorkoutEvent);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/by-type")
    public ResponseEntity<WorkoutTypeReportResponse> getWorkoutTypeReport(@RequestParam String workoutType,
                                                                          @RequestParam LocalDate month) {

        WorkoutType workoutTypeReport = workoutService.getWorkoutTypeReport(workoutType, month);
        WorkoutTypeReportResponse workoutTypeReportResponse = DtoMapper.fromWorkoutType(workoutTypeReport);

        return ResponseEntity.ok(workoutTypeReportResponse);
    }

    @GetMapping("/by-trainer")
    public ResponseEntity<TrainerReportResponse> getTrainerReport(@RequestParam UUID trainerId,
                                                                  @RequestParam LocalDate month) {

        WorkoutTrainer trainerReport = workoutService.getTrainerReport(trainerId, month);
        TrainerReportResponse trainerReportResponse = DtoMapper.fromWorkoutTrainer(trainerReport);

        return ResponseEntity.ok(trainerReportResponse);
    }

    @GetMapping("/general")
    public ResponseEntity<WorkoutGeneralReportResponse> getWorkoutGeneralReport(@RequestParam LocalDate fromMonth,
                                                                                @RequestParam LocalDate toMonth) {

        WorkoutGeneral workoutGeneralReport = workoutService.getWorkoutGeneralReport(fromMonth, toMonth);
        WorkoutGeneralReportResponse workoutGeneralReportResponse = DtoMapper
                .fromWorkoutGeneral(workoutGeneralReport, fromMonth, toMonth);

        return ResponseEntity.ok(workoutGeneralReportResponse);
    }
}
