package com.capgemini.rockGuitars.persistence;

import com.capgemini.rockGuitars.model.Guitar;
import org.hibernate.dialect.FirebirdDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class GuitarRepository {


    //In real life there would be a database here, like Oracle, MySQL or Postgres

    private long lastId = 0;

    private Map<Long, Guitar> guitars = new HashMap<>();

    @PostConstruct
    public void init(){
        for(int i = 0; i < 5; i++){
            Guitar guitar = new Guitar();
            guitar.setName("Twonky " + (i + 1));
            guitar.setNumberOfStrings(6);
            guitar.setId(i + 1);

            this.save(guitar);
        }
    }


    public void save(Guitar newGuitar){

        newGuitar.setId(++lastId);
        this.guitars.put(newGuitar.getId(), newGuitar);
    }



    public Guitar findById(long id){

        return this.guitars.get(id);
    }

    public void deleteById(long id){

        this.guitars.remove(id);
    }

    public Guitar update(long id, Guitar update){

        Guitar directObject = this.findById(id);  //directObject = lijdend voorwerp
        directObject.setName(update.getName());
        directObject.setNumberOfStrings(update.getNumberOfStrings());

        return directObject;
    }

    public Collection findAll(){

        return this.guitars.values();
    }
}


