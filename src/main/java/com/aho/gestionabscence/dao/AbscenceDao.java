package com.aho.gestionabscence.dao;

import com.aho.gestionabscence.model.Abscence;
import com.aho.gestionabscence.model.Etudiant;
import com.aho.gestionabscence.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AbscenceDao extends JpaRepository<Abscence, Long> {

    @Query("SELECT count(id) FROM Abscence u WHERE u.etudiant_id = 1 and u.matiere_id = 1")
    Collection<Abscence> findAllAbscence();
}
