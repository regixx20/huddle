package myapp.repository;

import myapp.model.Poll;
import myapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface PollRepository extends JpaRepository<Poll, Long>{

    Optional<Poll> findById(Long userId);
    List<Poll> findByTitle(String title);

    List<Poll> findByDescription(String description);
    List<Poll> findByLocation(String location);
    List<Poll> findByLimitDate(String location);

    List<Poll> findByCreator(User creator);

}
