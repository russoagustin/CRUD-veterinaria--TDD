package com.russo.veterinaria.model;


import org.springframework.data.annotation.Id;

public class Pet {
    @Id
    Long id;
    String name;
    String breed;
    String species;
    String color;


    public Pet() {
    }

    public Pet(Long id, String name, String breed, String species, String color) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.species = species;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
