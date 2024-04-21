package myapp;

import myapp.model.Slot;
import myapp.repository.SlotRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class SlotRepositoryTest {

    @Autowired
    private SlotRepository slotRepository;

    @BeforeEach
    void setUp() {
        slotRepository.deleteAll();
    }

    @AfterEach()
    void tearDown() {
        slotRepository.deleteAll();
    }

    @Test
    void testfindById() {
        Slot slot = new Slot();
        slotRepository.save(slot);
        assertEquals(slot.getId(), slotRepository.findById(slot.getId()).get().getId());
    }

    @Test
    void testfindAll() {
        Slot slot = new Slot();
        slotRepository.save(slot);
        Slot slot2 = new Slot();
        slotRepository.save(slot2);
        Slot slot3 = new Slot();
        slotRepository.save(slot3);
        assertEquals(3, slotRepository.findAll().size());
    }
}
