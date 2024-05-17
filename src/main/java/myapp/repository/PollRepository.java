package myapp.repository;

import myapp.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository

public interface PollRepository extends JpaRepository<Poll, Long>{

    List<Poll> findByTitle(String title);

    List<Poll> findByDescription(String description);
    List<Poll> findByLocation(String location);
    List<Poll> findByLimitDate(Date limitDate);

    //List<Poll> findByCreator(User creator);

    Poll findById(String id);


    void deleteById(String id);

}
