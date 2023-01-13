package com.example.c2dog.controller;

import com.example.c2dog.entity.Dog;
import com.example.c2dog.service.DogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DogController {

    public final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping("/dogs")
    public ResponseEntity<List<Dog>> getAllDogs() {
        List<Dog> list = dogService.retrieveDogs();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/dogs/breed")
    public ResponseEntity<List<String>> getDogBreeds() {
        List<String> list = dogService.retrieveDogBreed();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}/breed")
    public ResponseEntity<String> getBreedById(@PathVariable Long id) {
        String breed = dogService.retrieveDogBreedById(id);
        return new ResponseEntity<>(breed, HttpStatus.OK);
    }

    @GetMapping("/dog/name")
    public ResponseEntity<List<String>> getDogNames() {
        List<String> list = dogService.retrieveDogNames();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
