package myapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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





}
