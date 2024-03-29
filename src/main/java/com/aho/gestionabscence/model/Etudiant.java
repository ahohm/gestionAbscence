package com.aho.gestionabscence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.swing.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Etudiant {

    @Id
    @NotNull(message = "Matricule is mandatory must have exactly 5 caracter")
    @Size(min = 5, max = 5)
    private String matricule;

    @NotBlank(message = "Name is mandatory")
    @Size(min=3, max=30)
    private String nom;

    @NotBlank(message = "last name is mandatory")
    @Size(min=3, max=30)
    private String prenom;

    @NotBlank(message = "last name is mandatory")
    @Email(message = "Email should be valid")
    private String email;

//    @Past(message = "date od birth must be a past date")
    private LocalDate dateDeNaissance;


    @JsonIgnore
    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Abscence> abscences;

    @JsonIgnore
    @ManyToOne
    private Classe classe;

    @JsonIgnore
    @OneToMany(mappedBy ="etudiant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Elimination> eliminations;

    @Pattern(regexp = "(\\+216|00)[0-9]{8}")
    private String phoneNumber;

    public Etudiant(@NotNull(message = "Matricule is mandatory must have exactly 5 caracter")
                    @Size(min = 5, max = 5) String matricule,
                    @NotBlank(message = "Name is mandatory")
                    @Size(min = 3, max = 30) String nom,
                    @NotBlank(message = "last name is mandatory")
                    @Size(min = 3, max = 30) String prenom,
                    @NotBlank(message = "last name is mandatory")
                    @Email(message = "Email should be valid") String email,
                    LocalDate dateDeNaissance,
                    @Pattern(regexp = "(\\+216|00)[0-9]{8}") String phoneNumber) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateDeNaissance = dateDeNaissance;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "matricule='" + matricule + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", dateDeNaissance=" + dateDeNaissance +
                '}';
    }
}
