package fitnessreportssvc.service;

import fitnessreportssvc.model.WorkoutTypeReport;
import fitnessreportssvc.repository.WorkoutTypeRepository;
import fitnessreportssvc.web.dto.UpsertWorkoutTypeReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WorkoutReportService {

    private final WorkoutTypeRepository workoutTypeRepository;

    public WorkoutReportService(WorkoutTypeRepository workoutTypeRepository) {
        this.workoutTypeRepository = workoutTypeRepository;
    }

    public void upsertWorkoutTypeStats(UpsertWorkoutTypeReport dto) {

        WorkoutTypeReport typeReport = workoutTypeRepository
                .findByWorkoutTypeAndMonth(dto.getWorkoutType(), dto.getMonth())
                .orElse(new WorkoutTypeReport(null, dto.getWorkoutType(), dto.getMonth(), 0, 0.0, 0));

        typeReport.setTotalWorkouts(typeReport.getTotalWorkouts() + 1);
        typeReport.setTotalParticipants(typeReport.getTotalParticipants() + dto.getParticipants());
        typeReport.setAverageDuration((typeReport.getAverageDuration() *
                (typeReport.getTotalWorkouts() - 1) + dto.getDuration()) / typeReport.getTotalWorkouts());

        workoutTypeRepository.save(typeReport);
        log.info("WorkoutTypeReport saved for workout type {}", dto.getWorkoutType());
    }
}
