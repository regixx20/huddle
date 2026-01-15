package myapp.dto;

import java.util.List;

public record DashboardDto(
        UserSummaryDto user,
        List<PollSummaryDto> myPolls,
        List<PollSummaryDto> participatedPolls
) {}
