package myapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Participant participant;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Slot slot;

    private String vote;

    @ManyToOne(fetch = FetchType.LAZY)
    private Poll poll;




}
