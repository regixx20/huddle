package myapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import myapp.service.CustomLocalDateTimeDeserializer;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Slot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime start;

    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime end;

    @ManyToOne(fetch = FetchType.LAZY)
    private Poll poll;



    public Slot(String s, String s1) {
        this.start = LocalDateTime.parse(s);
        this.end = LocalDateTime.parse(s1);
    }
}
