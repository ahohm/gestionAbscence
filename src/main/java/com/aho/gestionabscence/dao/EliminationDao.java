package com.aho.gestionabscence.dao;

import com.aho.gestionabscence.model.Elimination;
import com.aho.gestionabscence.model.Etudiant;
import com.aho.gestionabscence.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EliminationDao extends JpaRepository<Elimination, Long> {

    List<Elimination> findAllByMatiereAndAndEtudiant(Matiere matiere, Etudiant etudiant);
    List<Elimination> findAllByEtudiant(Etudiant etudiant);
}
