package com.example.petservice.controller;

import com.example.petservice.model.Pet;
import com.example.petservice.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * Controller pour les requêtes http
 */

@RestController
public class PetController {

    private PetRepository dao;
    @Value("${me}")
    private String me;

    @Autowired
    public PetController(PetRepository dao) {
        this.dao = dao;
    }

    /**
     * Récupérer tous les animaux
     * @return la collection de tous les animaux
     */
    @ResponseBody
    @GetMapping(value = "/Pets")
    public Iterable<Pet> getPets(){
        return dao.findAll();
    }

    /**
     * Récupérer un animal
     * @param name nom de l'animal à récupérer
     * @return l'animal spécifié par le nom donné
     */
    @ResponseBody
    @GetMapping(value = "/Pets/name/{name}")
    public Iterable<Pet> getPet(@PathVariable String name){
        return dao.findByName(name);
    }

    /**
     * Récupérer un animal
     * @param id identifiant de l'animal à récupérer
     * @return l'animal spécifié par l'identifiant donné
     */
    @ResponseBody
    @GetMapping(value = "/Pets/{id}")
    public Pet getPet(@PathVariable int id){
        return dao.findById(id);
    }

    /**
     * Ajout d'un animal
     * @param p animal fourni au travers de l'API
     */
    @PostMapping(value = "Pets")
    public void addPet(@RequestBody Pet p){
        dao.save(p);
    }

    /**
     * Met à jour les informations d'un animal
     * @param id identifiant du profil de l'animal à mettre à jour
     * @param pet nouveau profil de l'aniaml
     * @return Le nouveau profil
     */
    @PutMapping(value = "/Pets/{id}")
    public Pet updatePet(@PathVariable int id, @RequestBody Pet pet) {
        return dao.save(pet);
    }

    @DeleteMapping(value = "Pets/{id}")
    public void deletePet(@PathVariable int id){
        dao.deleteById(id);
    }

    @GetMapping(value = "/Cestqui")
    public String getName(){
        return me;
    }
}
