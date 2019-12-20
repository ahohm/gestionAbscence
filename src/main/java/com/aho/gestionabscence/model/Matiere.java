package com.aho.gestionabscence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(min=3, max=30)
    private String libel;

    @Positive
    private float numberHours;

    @Positive
    private long permit;

    @OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Abscence> abscences;

    @OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Elimination> eliminations;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MATIERE_CLASSE",
            joinColumns = @JoinColumn(name = "MATIERE_ID"),
            inverseJoinColumns = @JoinColumn(name = "CLASSE_ID"))
    private List<Classe> classes;

    @Override
    public String toString() {
        return "Matiere{" +
                "id=" + id +
                ", libel='" + libel + '\'' +
                ", numberHours=" + numberHours +
                ", permit=" + permit +
                ", abscences=" + abscences +
                ", eliminations=" + eliminations +
                ", classes=" + classes +
                '}';
    }
}
