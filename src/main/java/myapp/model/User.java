package myapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    private String confirmPassword = password;

    @OneToMany(mappedBy = "creator", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Poll> polls;

    @ManyToMany(mappedBy = "participatedUsers", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Poll> participatedPolls = new ArrayList<>();


    public String getFullName() {
        return firstName + " " + lastName;
    }
    public String getPasswordConfirm() {
        return password;
    }




}