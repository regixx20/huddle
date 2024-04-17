package myapp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

//utilisateur
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String nom;
    @Getter
    private String email;

    public User(String nom, String email){
        this.nom = nom;
        this.email = email;

    }

    public User() {

    }


}
