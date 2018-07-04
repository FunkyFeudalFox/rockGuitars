package com.capgemini.rockGuitars.persistence;

import com.capgemini.rockGuitars.model.Guitar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuitarRepository extends CrudRepository<Guitar, Long> {
}
