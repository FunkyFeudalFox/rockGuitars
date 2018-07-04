package com.capgemini.rockGuitars.rest;

import com.capgemini.rockGuitars.model.Guitar;
import com.capgemini.rockGuitars.persistence.GuitarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/guitars")
public class GuitarController {


    @Autowired
    private GuitarRepository guitarRepository;

    /*
    @GetMapping("name")
    public String getHello(){
        return "Hello world!";
    }

    @GetMapping("guitar")
    public Guitar getGuitar() {
        Guitar bcRichWarlock = new Guitar();
        bcRichWarlock.setNumberOfStrings(6);
        bcRichWarlock.setName("bcRichRedWarlock");

        return bcRichWarlock;
    }
    */

    @PostMapping
    public ResponseEntity<Guitar> create(@RequestBody Guitar newGuitar){

        newGuitar.setId(++Guitar.lastId);
        this.guitarRepository.save(newGuitar);

        return new ResponseEntity<Guitar>(newGuitar, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Guitar>> guitarList(){
        return new ResponseEntity<Iterable<Guitar>>(this.guitarRepository.findAll(), HttpStatus.OK );
    }

    @GetMapping("{id}")
    public ResponseEntity<Guitar> findById(@PathVariable long id) {
        Optional<Guitar> result = this.guitarRepository.findById(id);
        if (result.isPresent()){
            return new ResponseEntity<Guitar>(result.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Guitar> updateById(@PathVariable long id, @RequestBody Guitar update){

        Optional<Guitar> possibleDirectObject = this.guitarRepository.findById(id);

        if (possibleDirectObject.isPresent()) {
            Guitar directObject = possibleDirectObject.get();

            directObject.setName(update.getName());
            directObject.setNumberOfStrings(update.getNumberOfStrings());
            directObject.setId(update.getId());

            directObject = this.guitarRepository.save(directObject);

            return new ResponseEntity<Guitar>(this.guitarRepository.save(directObject),
                    HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Guitar>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id){
        this.guitarRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
