package myapp.controller.api;

import myapp.model.Poll;
import myapp.service.PollService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polls")
public class PollApiController {

    private final PollService pollService;

    public PollApiController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping
    public List<Poll> list() {
        return pollService.findAllPolls();
    }

    @GetMapping("/{id}")
    public Poll get(@PathVariable String id) {
        return pollService.findPollById(id);
    }
}
