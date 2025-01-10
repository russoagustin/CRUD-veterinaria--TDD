package com.russo.veterinaria.controller;

import com.russo.veterinaria.model.Pet;
import com.russo.veterinaria.repository.PetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pets")
public class PetController {
    private PetRepository repository;

    private PetController(PetRepository repository){
        this.repository = repository;
    }

    @GetMapping
    ResponseEntity<Iterable<Pet>> getAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Pet> findById(@PathVariable Long id){
        Optional<Pet> petOptional = repository.findById(id);
        return petOptional.isPresent() ?
                ResponseEntity.ok(petOptional.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    ResponseEntity<Void> createAPet(@RequestBody Pet newPet, UriComponentsBuilder ucb){
        Pet savedPet = repository.save(newPet);
        URI uri = ucb.path("pets/id").buildAndExpand(savedPet.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
