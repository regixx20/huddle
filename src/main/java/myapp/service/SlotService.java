package myapp.service;

import myapp.model.Slot;
import myapp.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotService {

    @Autowired
    private SlotRepository slotRepository;

    public List<Slot> findAllSlots() {
        return slotRepository.findAll();
    }

    public void saveSlot(Slot s) {
        slotRepository.save(s);
    }

    public Slot findSlotById(Long id) {
        return slotRepository.findById(id).orElse(null);
    }

    public void deleteSlot(Slot s) {
        slotRepository.delete(s);
    }


}
