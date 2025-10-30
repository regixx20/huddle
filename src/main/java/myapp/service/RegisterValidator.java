package myapp.service;


import myapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Service
public class RegisterValidator implements Validator {

    @Autowired
    private UserService userService;
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
                "user.firstName", "Le champ Prénom est requis.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
                "user.lastName", "Le champ Nom est requis.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
                "user.email", "Le champ email est requis.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "user.password", "Le champ mot de passe est requis.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
                "user.passwordConfirmation", "Le champ confirmation du mot de passe est requis.");

        //TAILLE DU MOT DE PASSE
        if (user.getPassword().length() < 6) {
            errors.rejectValue("password", "user.password.size",
                    "Le mot de passe doit contenir au moins 6 caractères.");
        }
        //CONFIRMATION DU MOT DE PASSE
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "user.passwordConfirmation",
                    "Les mots de passe ne correspondent pas.");
        }
        if(userService.findUserByEmail(user.getEmail()) != null){
            errors.rejectValue("email", "user.email",
                    "Cet email est déjà utilisé.");
        }

    }
}
