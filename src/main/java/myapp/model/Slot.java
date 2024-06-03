package myapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.ToString;
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


    @OneToMany(mappedBy = "slot", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Vote> votes = new ArrayList<>();

    private boolean isChosen = false;



    @Transient
    public String getDayOfWeek() {
        if (start != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE", Locale.FRENCH);
            String day = start.format(formatter);
            return day.substring(0, 1).toUpperCase(Locale.FRENCH) + day.substring(1).toLowerCase(Locale.FRENCH);
        }
        return null;
    }

    @Transient
    public String getMonth() {
        if (start != null) {
            return start.getMonth().getDisplayName(TextStyle.FULL, Locale.FRENCH);
        }
        return null;
    }


    public String getVote(Participant participant) {
        for (Vote v : votes) {
            if (v.getParticipant().equals(participant)) {
                return v.getVote();
            }
        }
        return null;
    }
}
