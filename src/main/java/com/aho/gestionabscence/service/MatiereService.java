package com.aho.gestionabscence.service;

import com.aho.gestionabscence.dao.MatiereDao;
import com.aho.gestionabscence.model.Classe;
import com.aho.gestionabscence.model.Matiere;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MatiereService {

    private MatiereDao matiereDao;

    public Matiere save(Matiere matiere){
        return matiereDao.save(matiere);
    }

    public List<Matiere> findAll(){
        return matiereDao.findAll();
    }

    public Optional<Matiere> getOneById(long id){
        return matiereDao.findById(id);
    }

    public Matiere update(long id, Matiere matiere){
        return matiereDao.save(matiere);
    }

    public void delete(long id){
        Matiere matiere = getOneById(id).get();
        matiereDao.delete(matiere);
    }

    public List<Matiere> findByClasse(Classe classe){
        return matiereDao.findAllByClasses(classe);
    }
}
