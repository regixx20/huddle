package myapp.service;

import myapp.model.Poll;
import myapp.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    public List<Poll> findAllPolls() {
        return pollRepository.findAll();
    }

    public Poll findPollById(Long id) {
        return pollRepository.findById(id).orElse(null);
    }

    public List<Poll> findPollByTitle(String title) {
        return pollRepository.findByTitle(title);
    }

    public List<Poll> findPollByDescription(String description) {
        return pollRepository.findByDescription(description);
    }

    public List<Poll> findPollByLocation(String location) {
        return pollRepository.findByLocation(location);
    }

    public void savePoll(Poll p) {
        pollRepository.save(p);
    }

    public void deletePoll(Poll p) {
        pollRepository.delete(p);
    }




}
