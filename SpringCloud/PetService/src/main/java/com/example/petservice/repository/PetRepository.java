package com.example.petservice.repository;

import com.example.petservice.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Integer> {

    Iterable<Pet> findByName(String name);
    Pet findById(int id);
    void deleteById(int id);
}
