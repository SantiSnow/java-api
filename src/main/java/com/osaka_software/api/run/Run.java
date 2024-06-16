package com.osaka_software.api.run;

import java.time.LocalDateTime;

public record Run(
        Integer id,
        String title,
        Integer kilometers,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Location location
) { }
