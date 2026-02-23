package myapp.dto;



public record PollDetailsDto(
  PollDto poll,
  List<SlotDto> slots,
  List<ParticipantDto> participants,
  List<VoteDto> votes
) {}