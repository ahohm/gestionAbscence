package com.aho.gestionabscence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Elimination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Etudiant etudiant;

    @ManyToOne
    private Matiere matiere;

    private boolean isEliminated;

    public Elimination(Etudiant etudiant, Matiere matiere, boolean isEliminated) {
        this.etudiant = etudiant;
        this.matiere = matiere;
        this.isEliminated = isEliminated;
    }

    @Override
    public String toString() {
        return "Elimination{" +
                "id=" + id +
                ", etudiant=" + etudiant +
                ", matiere=" + matiere +
                ", isEliminated=" + isEliminated +
                '}';
    }
}
