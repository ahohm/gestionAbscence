package com.aho.gestionabscence.dao;

import com.aho.gestionabscence.model.Abscence;
import com.aho.gestionabscence.model.Etudiant;
import com.aho.gestionabscence.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AbscenceDao extends JpaRepository<Abscence, Long> {

    List<Abscence> findAllByMatiereAndEtudiant(Matiere matiere, Etudiant etudiant);


}
