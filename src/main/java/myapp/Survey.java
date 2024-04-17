package myapp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

//sondage
@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String title;
    @Getter
    private String description;

    public Survey() {}
    public Survey(String title, String description) {
        this.title = title;
        this.description=description;
    }

}
