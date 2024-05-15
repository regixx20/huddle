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
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @OneToMany( fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
    @ToString.Exclude
    private List<Slot> slots;

    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "user_id") // Nom de la colonne dans la table Poll faisant référence à l'utilisateur créateur
    private User creator;

    private int numberOfParticipants;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Participant> participants;

}
