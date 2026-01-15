package myapp.dto;

public record MeDto(
        boolean authenticated,
        UserSummaryDto user
) {}
