package myapp.controller.api;

import myapp.dto.MeDto;
import myapp.dto.DtoMappers;
import myapp.model.User;
import myapp.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class AuthApiController {

    private final UserService userService;

    public AuthApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public MeDto me(Principal principal) {
        if (principal == null) {
            return new MeDto(false, null);
        }
        User user = userService.findUserByEmail(principal.getName());
        return new MeDto(true, DtoMappers.toUserSummary(user));
    }
}
