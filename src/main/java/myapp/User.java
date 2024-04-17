package myapp;
import lombok.Getter;
import org.springframework.stereotype.Component;

//utilisateur
@Getter
@Component
public class User {

    private Long id;
    private String nom;
    private String email;



    public User(String nom, String email){
       this.nom = nom;
       this.email = email;

    }

    public User() {

    }


}
