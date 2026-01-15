package myapp.dto;

import myapp.model.Poll;
import myapp.model.User;


public class DtoMappers {
    private DtoMappers() {}

    public static UserSummaryDto toUserSummary(User u) {
        if (u == null) return null;
        return new UserSummaryDto(
                u.getId(),
                u.getEmail(),
                u.getFirstName(),
                u.getLastName(),
                u.getFullName()
        );
    }

    public static PollSummaryDto toPollSummary(Poll p) {
        if (p == null) return null;
        return new PollSummaryDto(
                p.getId(),
                p.getTitle(),
                p.getLocation(),
                p.getLimitDate(),
                p.isDecided()
        );
    }
}
