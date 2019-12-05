package com.aho.gestionabscence.api;

import com.aho.gestionabscence.model.Matiere;
import com.aho.gestionabscence.service.MatiereService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/matiere")
@AllArgsConstructor
public class MatiereCtrl {

    private MatiereService matiereService;

    @PostMapping("/add")
    public ResponseEntity<Matiere> add(@Valid @RequestBody Matiere matiere){
        try{
            return new ResponseEntity(matiereService.save(matiere), HttpStatus.OK);
        }
        catch( Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping({"/",""})
    public ResponseEntity<List<Matiere>> getAll(){
        try {
            return new ResponseEntity(matiereService.findAll(),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matiere> getOne(@PathVariable long id){
        try{
            return new ResponseEntity(matiereService.getOneById(id),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matiere> update(@PathVariable long id, @RequestBody Matiere matiere){
        try{
            return new ResponseEntity(matiereService.update(id, matiere),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void>  delete(@PathVariable long id){
        try{
            matiereService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
