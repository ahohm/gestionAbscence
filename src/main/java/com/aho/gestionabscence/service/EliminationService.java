package com.aho.gestionabscence.service;

import com.aho.gestionabscence.dao.EliminationDao;
import com.aho.gestionabscence.model.Elimination;
import com.aho.gestionabscence.model.Etudiant;
import com.aho.gestionabscence.model.Matiere;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EliminationService {

    private EliminationDao eliminationDao;

    public Elimination save(Elimination elimination){
        return eliminationDao.save(elimination  );
    }

    public List<Elimination> findAll(){
        return eliminationDao.findAll();
    }

    public Optional<Elimination> getOneById(long id){
        return eliminationDao.findById(id);
    }

    public Elimination update(long id, Elimination classe){
        return eliminationDao.save(classe);
    }

    public void delete(long id){
        Elimination etudiant = getOneById(id).get();
        eliminationDao.delete(etudiant);
    }


    public List<Matiere> findEliminatedEtudiant(Etudiant etudiant){
        List<Matiere> matieres = new ArrayList<>();

         for(Elimination e : eliminationDao.findAllByEtudiant(etudiant)){
             matieres.add(e.getMatiere());
         }
         return matieres;
    }
}
