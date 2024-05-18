package myapp.service;

import myapp.model.Participant;
import myapp.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    public void saveParticipant(Participant p) {
        participantRepository.save(p);
    }


}
