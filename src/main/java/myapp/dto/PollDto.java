package myapp.dto;

import java.util.Date;

public record PollDto(
  String id,
  String title,
  String description,
  String location,
  Date limitDate,
  int numParticipants,
  boolean decided
) {}