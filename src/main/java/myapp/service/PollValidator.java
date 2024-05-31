package myapp.service;

import myapp.model.Poll;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;

@Service
public class PollValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Poll.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Poll poll = (Poll) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title",
                "poll.title", "Le champ Titre est requis.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
                "poll.description", "Le champ description est requis.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "limitDate",
                "poll.limitDate", "Le champ date limite est requis.");

        if (poll.getLimitDate() != null && poll.getLimitDate().before(new Date())) {
            errors.rejectValue("limitDate", "poll.limitDate.past",
                    "La date limite ne peut pas être dans le passé.");
        }


        if (poll.getSlots() == null || poll.getSlots().isEmpty()) {
            errors.rejectValue("slots", "poll.size.unauthorized",
                    "No slot found.");
        }

    }
}


