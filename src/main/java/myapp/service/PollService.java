package myapp.service;

import jakarta.transaction.Transactional;
import myapp.model.Poll;
import myapp.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@Service

public class PollService {

    @Autowired
    private PollRepository pollRepository;

    public List<Poll> findAllPolls() {
        return pollRepository.findAll();
    }

    public Poll findPollById(String id) {
        return pollRepository.findById(id);
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
