package com.aho.gestionabscence.api;

import com.aho.gestionabscence.model.Abscence;
import com.aho.gestionabscence.service.AbscenceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/abscence")
@AllArgsConstructor
public class AbscenceCtrl {

    private AbscenceService abscenceService;

    @PostMapping("/add")
    public ResponseEntity<Abscence> add(@Valid @RequestBody Abscence abscence){
        try{
            return new ResponseEntity(abscenceService.save(abscence), HttpStatus.OK);
        }
        catch( Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping({"/",""})
    public ResponseEntity<List<Abscence>> getAll(){
        try {
            return new ResponseEntity(abscenceService.findAll(),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Abscence> getOne(@PathVariable long id){
        try{
            return new ResponseEntity(abscenceService.getOneById(id),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Abscence> update(@PathVariable long id, @RequestBody Abscence abscence){
        try{
            return new ResponseEntity(abscenceService.update(id, abscence),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void>  delete(@PathVariable long id){
        try{
            abscenceService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
