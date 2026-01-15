package myapp.dto;

public record UserSummaryDto(
        Long id,
        String email,
        String firstName,
        String lastName,
        String fullName
) {}