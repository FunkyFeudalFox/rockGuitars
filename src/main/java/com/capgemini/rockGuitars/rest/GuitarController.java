package com.capgemini.rockGuitars.rest;

import com.capgemini.rockGuitars.model.Guitar;
import com.capgemini.rockGuitars.persistence.GuitarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/guitars")
public class GuitarController {


    @Autowired
    private GuitarRepository guitarRepository;

    @GetMapping("name")
    public String getHello(){
        return "Hello world!";
    }

    /*
    @GetMapping("guitar")
    public Guitar getGuitar() {
        Guitar bcRichWarlock = new Guitar();
        bcRichWarlock.setNumberOfStrings(6);
        bcRichWarlock.setName("bcRichRedWarlock");

        return bcRichWarlock;
    }
    */

    @PostMapping
    public Guitar create(@RequestBody Guitar newGuitar){

        newGuitar.setId(++Guitar.lastId);
        this.guitarRepository.save(newGuitar);

        return newGuitar;
    }

    @GetMapping
    public Collection<Guitar> guitarList(){
        return this.guitarRepository.findAll();
    }

    @GetMapping("{id}")
    public Guitar findById(@PathVariable long id){
        Guitar result = this.guitarRepository.findById(id);
        return result;
    }

    @PutMapping("{id}")
    public Guitar updateById(@PathVariable long id, @RequestBody Guitar update){

        return this.guitarRepository.update(id, update);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable long id){
        this.guitarRepository.deleteById(id);
    }


}
