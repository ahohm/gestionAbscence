package com.aho.gestionabscence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Abscence> abscences;

    @ManyToOne
    private Classe classe;

    public Etudiant(@NotNull(message = "Matricule is mandatory must have exactly 5 caracter")
                    @Size(min = 5, max = 5) String matricule,
                    @NotBlank(message = "Name is mandatory")
                    @Size(min = 3, max = 30) String nom,
                    @NotBlank(message = "last name is mandatory")
                    @Size(min = 3, max = 30) String prenom,
                    @NotBlank(message = "last name is mandatory")
                    @Email(message = "Email should be valid") String email,
                    LocalDate dateDeNaissance) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateDeNaissance = dateDeNaissance;
    }
}
