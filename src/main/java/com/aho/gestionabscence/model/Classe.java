package com.aho.gestionabscence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(min=3, max=30)
    private String libel;

    @Size(min=3, max=30)
    private String nomComplet;

    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Abscence> abscences;

    @ManyToMany(mappedBy = "classes")
    private List<Matiere> matieres;

    @OneToMany
    private List<Etudiant> etudiants;

}
