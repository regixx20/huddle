package myapp.controller;

import myapp.model.Slot;
import myapp.repository.SlotRepository;
import myapp.service.SlotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/slots")
public class SlotController {

    @Autowired
    private SlotService slotService;

    @GetMapping("")
    public String listSlots() {
        return "slotsList";
    }

    @ModelAttribute("slots")
    Collection<Slot> slots() {
        return slotService.findAllSlots();
    }



}
