package myapp;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


//sondage
@Getter
@Component
public class Survey {
    private Long id;
    @Setter
    private String title;
    @Setter
    private String description;

    public Survey() {}
    public Survey(String title, String description) {
        this.setTitle(title);
        this.setDescription(description);
    }

}
