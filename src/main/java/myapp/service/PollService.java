package myapp.service;

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
        /*if (p.getId() == null || p.getId().trim().isEmpty()) {
            p.setId(generateRandomString(16)); // Génère un ID de longueur 16
        }*/
        pollRepository.save(p);
    }

    public void deletePoll(Poll p) {
        pollRepository.delete(p);
    }

    private String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException("La longueur doit être >= 1");

        String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new SecureRandom();
        char[] buf = new char[length];
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols.charAt(random.nextInt(symbols.length()));
        return new String(buf);
    }
}
