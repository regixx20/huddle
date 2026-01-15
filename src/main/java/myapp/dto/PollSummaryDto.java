package myapp.dto;

import java.util.Date;

public record PollSummaryDto(
        String id,
        String title,
        String location,
        Date limitDate,
        boolean decided
) {}
