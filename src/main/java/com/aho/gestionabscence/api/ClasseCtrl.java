package com.aho.gestionabscence.api;

import com.aho.gestionabscence.model.Classe;
import com.aho.gestionabscence.service.ClasseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/classe")
@AllArgsConstructor
public class ClasseCtrl {

    private ClasseService classeService;


    @PostMapping("/add")
    public ResponseEntity<Classe> add(@Valid @RequestBody Classe classe){
        try{
            return new ResponseEntity(classeService.save(classe), HttpStatus.OK);
        }
        catch( Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping({"/",""})
    public ResponseEntity<List<Classe>> getAll(){
        try {
            return new ResponseEntity(classeService.findAll(),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classe> getOne(@PathVariable long id){
        try{
            return new ResponseEntity(classeService.getOneById(id),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classe> update(@PathVariable long id, @RequestBody Classe classe){
        try{
            return new ResponseEntity(classeService.update(id, classe),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void>  delete(@PathVariable long id){
        try{
            classeService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
