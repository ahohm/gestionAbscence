package com.aho.gestionabscence.dao;

import com.aho.gestionabscence.model.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseDao extends JpaRepository<Classe, Long> {
}
