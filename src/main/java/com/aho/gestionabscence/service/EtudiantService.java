package com.aho.gestionabscence.service;

import com.aho.gestionabscence.dao.EtudiantDao;
import com.aho.gestionabscence.model.Etudiant;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EtudiantService {

    private EtudiantDao etudiantDao;

    public Etudiant save(Etudiant etudiant){
        return etudiantDao.save(etudiant);
    }

    public List<Etudiant> findAll(){
        return etudiantDao.findAll();
    }

    public Optional<Etudiant> getOneById(String matricule){
        return etudiantDao.findByMatricule(matricule);
    }

    public Etudiant update(String matricule, Etudiant etudiant){
        return etudiantDao.save(etudiant);
    }

    public void delete(String matricule){
        Etudiant etudiant = getOneById(matricule).get();
        etudiantDao.delete(etudiant);
    }

}
