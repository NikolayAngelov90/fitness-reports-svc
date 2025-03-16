package fitnessreportssvc.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpsertWorkoutTypeReport {

    @NotNull
    String workoutType;

    @NotNull
    LocalDate month;

    @NotNull
    int duration;

    @NotNull
    int participants;
}
