package myapp.model;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

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
                "poll.title", "Le champs Titre est requis.");


       /*  if (!(poll.getSlots().size()<0)) {
            errors.rejectValue("slots", "poll.size.unauthorized",
                    "No slot found.");
        }*/

    }
}
