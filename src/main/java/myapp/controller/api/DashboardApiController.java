package myapp.controller.api;

import myapp.dto.DashboardDto;
import myapp.dto.DtoMappers;
import myapp.dto.PollSummaryDto;
import myapp.model.User;
import myapp.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DashboardApiController {

    private final UserService userService;

    public DashboardApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public DashboardDto dashboard(Principal principal) {
        // Ici, on suppose que /api/dashboard est protégé (auth obligatoire)
        User user = userService.findUserByEmail(principal.getName());

        List<PollSummaryDto> myPolls = (user.getPolls() == null ? List.of() :
                user.getPolls().stream().map(DtoMappers::toPollSummary).toList());

        List<PollSummaryDto> participated = (user.getParticipatedPolls() == null ? List.of() :
                user.getParticipatedPolls().stream().map(DtoMappers::toPollSummary).toList());

        return new DashboardDto(DtoMappers.toUserSummary(user), myPolls, participated);
    }
}
