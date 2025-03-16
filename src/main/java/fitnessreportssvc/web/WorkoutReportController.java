package fitnessreportssvc.web;

import fitnessreportssvc.service.WorkoutReportService;
import fitnessreportssvc.web.dto.UpsertWorkoutTypeReport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/workouts-report")
public class WorkoutReportController {

    private final WorkoutReportService workoutReportService;

    public WorkoutReportController(WorkoutReportService workoutReportService) {
        this.workoutReportService = workoutReportService;
    }

    @PostMapping("/types")
    public ResponseEntity<Void> upsertWorkoutTypeReport(@RequestBody UpsertWorkoutTypeReport upsertWorkoutTypeReport) {

        workoutReportService.upsertWorkoutTypeStats(upsertWorkoutTypeReport);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }
}
