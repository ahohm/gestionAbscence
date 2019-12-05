package com.aho.gestionabscence.service;

import com.aho.gestionabscence.dao.AbscenceDao;
import com.aho.gestionabscence.model.Abscence;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AbscenceService {

    private AbscenceDao abscenceDao;

    public Abscence save(Abscence abscence){
        return abscenceDao.save(abscence);
    }

    public List<Abscence> findAll(){
        return abscenceDao.findAll();
    }

    public Optional<Abscence> getOneById(long id){
        return abscenceDao.findById(id);
    }

    public Abscence update(long id, Abscence abscence){
        return  abscenceDao.save(abscence);
    }

    public void delete(long id){
        Abscence abscence = getOneById(id).get();
        abscenceDao.delete(abscence);
    }

}
