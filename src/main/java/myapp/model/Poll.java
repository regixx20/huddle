package myapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poll implements Serializable {
    @Id
    //@GeneratedValue(generator = "UUID")
    //@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "custom-id-gen")
    @GenericGenerator(name = "custom-id-gen", strategy = "myapp.service.CustomIdService")
    private String id;



   // @NotBlank(message = "Le titre ne peut pas être vide")
    private String title;

    private String description;

    private String location;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date limitDate;

    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
    @ToString.Exclude
    private List<Slot> slots;

    @ManyToOne(fetch = FetchType.EAGER)
    private User creator;

    private int numberOfParticipants = 0;

    @OneToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Participant> participants = new ArrayList<>();

    @OneToMany(mappedBy = "poll", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Vote> votes;

    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<User> participatedUsers = new ArrayList<>();

    private boolean isDecided = false;

    public void decide() {
        isDecided = true;
    }
    public boolean isDecided() {
        return isDecided;
    }

    public List<String> participantMail(){
        List<String> mail = new ArrayList<>();
        for(Participant p : participants){
            mail.add(p.getEmail());
        }
        return mail;
    }

    public List<String> getEmails() {
        List<String> emails = new ArrayList<>();
        for (Participant participant : participants) {
            emails.add(participant.getEmail());
        }
        return emails;
    }

   /* public String getFormattedLimitDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(this.limitDate);
    }*/

}
