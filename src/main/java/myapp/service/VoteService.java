package myapp.service;

import myapp.model.Vote;
import myapp.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public List<Vote> findAllVotes() {
        return voteRepository.findAll();
    }

    public void saveVote(Vote v) {
        voteRepository.save(v);
    }


}
