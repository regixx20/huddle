package myapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poll implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   // @NotBlank(message = "Le titre ne peut pas être vide")
    private String title;

    private String description;

    private String location;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date limitDate;

    @OneToMany( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
    private List<Slot> slots;

    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "user_id") // Nom de la colonne dans la table Poll faisant référence à l'utilisateur créateur
    private User creator;

    private int numberOfParticipants;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Participant> participants;

}
