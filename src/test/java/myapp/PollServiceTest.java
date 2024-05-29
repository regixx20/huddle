package myapp;


import myapp.model.Poll;
import myapp.repository.PollRepository;
import myapp.service.PollService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PollServiceTest {

    @Mock
    private PollRepository pollRepository;

    @InjectMocks
    private PollService pollService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllPolls() {
        Poll poll1 = new Poll();
        poll1.setTitle("Poll 1");

        Poll poll2 = new Poll();
        poll2.setTitle("Poll 2");

        when(pollRepository.findAll()).thenReturn(Arrays.asList(poll1, poll2));

        List<Poll> polls = pollService.findAllPolls();
        assertEquals(2, polls.size());
        verify(pollRepository, times(1)).findAll();
    }

    @Test
    public void testFindPollById() {
        Poll poll = new Poll();
        poll.setTitle("Sample Poll");
        when(pollRepository.findByTitle("Sample Poll")).thenReturn(Arrays.asList(poll));

        List<Poll> polls = pollService.findPollByTitle("Sample Poll");
        assertEquals(1, polls.size());
        assertEquals("Sample Poll", polls.get(0).getTitle());
        verify(pollRepository, times(1)).findByTitle("Sample Poll");
    }

    @Test
    public void testFindPollByTitle() {
        Poll poll = new Poll();
        poll.setTitle("Sample Poll");
        when(pollRepository.findByTitle("Sample Poll")).thenReturn(Arrays.asList(poll));

        List<Poll> polls = pollService.findPollByTitle("Sample Poll");
        assertEquals(1, polls.size());
        assertEquals("Sample Poll", polls.get(0).getTitle());
        verify(pollRepository, times(1)).findByTitle("Sample Poll");
    }

    @Test
    public void testFindPollByDescription() {
        Poll poll = new Poll();
        poll.setDescription("Sample Description");
        when(pollRepository.findByDescription("Sample Description")).thenReturn(Arrays.asList(poll));

        List<Poll> polls = pollService.findPollByDescription("Sample Description");
        assertEquals(1, polls.size());
        assertEquals("Sample Description", polls.get(0).getDescription());
        verify(pollRepository, times(1)).findByDescription("Sample Description");
    }

    @Test
    public void testFindPollByLocation() {
        Poll poll = new Poll();
        poll.setLocation("Sample Location");
        when(pollRepository.findByLocation("Sample Location")).thenReturn(Arrays.asList(poll));

        List<Poll> polls = pollService.findPollByLocation("Sample Location");
        assertEquals(1, polls.size());
        assertEquals("Sample Location", polls.get(0).getLocation());
        verify(pollRepository, times(1)).findByLocation("Sample Location");
    }

    @Test
    public void testSavePoll() {
        Poll poll = new Poll();
        poll.setTitle("New Poll");
        pollService.savePoll(poll);
        verify(pollRepository, times(1)).save(poll);
    }

    @Test
    public void testDeletePoll() {
        Poll poll = new Poll();
        poll.setId("123");
        pollService.deletePoll(poll);
        verify(pollRepository, times(1)).delete(poll);
    }
}
