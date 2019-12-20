package com.aho.gestionabscence.service;

import com.aho.gestionabscence.dao.ClasseDao;
import com.aho.gestionabscence.dao.EtudiantDao;
import com.aho.gestionabscence.model.Classe;
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
    private ClasseDao classeDao;


    public Etudiant save(Etudiant etudiant, long classeid){

        Classe classe =  classeDao.getOne(classeid);
        etudiant.setClasse(classe);
        return etudiantDao.save(etudiant);
    }

    public List<Etudiant> findAll(){
        return etudiantDao.findAll();
    }

    public Optional<Etudiant> getOneById(String matricule){
        return etudiantDao.findByMatricule(matricule);
    }

    public Etudiant update(long classeid, Etudiant etudiant){

        return this.save(etudiant, classeid);
    }

    public boolean delete(String matricule){
        Etudiant etudiant = getOneById(matricule).get();
        try {
            etudiantDao.delete(etudiant);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
