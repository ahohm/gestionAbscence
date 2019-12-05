package com.aho.gestionabscence.dao;

import com.aho.gestionabscence.model.Classe;
import com.aho.gestionabscence.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatiereDao extends JpaRepository<Matiere, Long> {


    List<Matiere> findAllByClasses(Classe classe);
}
