package com.aho.gestionabscence.dao;

import com.aho.gestionabscence.model.Etudiant;
import com.aho.gestionabscence.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantDao extends JpaRepository<Etudiant, String> {
    Optional<Etudiant> findByMatricule(String matricule);

}
