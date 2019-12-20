package com.aho.gestionabscence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Size(min=3, max=30)
    private String libel;

    @Size(min=3, max=30)
    private String nomComplet;

    @JsonIgnore
    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Abscence> abscences;

    @JsonIgnore
    @ManyToMany(mappedBy = "classes")
    private List<Matiere> matieres;

    @JsonIgnore
    @OneToMany
    private List<Etudiant> etudiants;

    @Override
    public String toString() {
        return "Classe{" +
                "id=" + id +
                ", libel='" + libel + '\'' +
                ", nomComplet='" + nomComplet + '\'' +
                ", abscences=" + abscences +
                ", matieres=" + matieres +
                ", etudiants=" + etudiants +
                '}';
    }
}
