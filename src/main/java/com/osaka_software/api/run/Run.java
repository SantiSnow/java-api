package com.osaka_software.api.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public record Run(
        Integer id,
        @NotEmpty
        String title,
        @Positive
        Integer kilometers,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Location location
) {

        public Run {
                if(startTime.isAfter(endTime))
                {
                        throw new IllegalArgumentException("Start time must be before end time");
                }
        }

}
