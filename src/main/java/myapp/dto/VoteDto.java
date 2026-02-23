package myapp.dto;

public record VoteDto(Long id, Long participantId, Long slotId, String value) {}