package com.example.c2dog.resolver;

import com.example.c2dog.entity.Dog;
import com.example.c2dog.repository.DogRepository;
import com.example.c2dog.exception.DogNotFoundException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Component
public class Query {

    private final DogRepository dogRepository;

    public Query(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @QueryMapping
    public Iterable<Dog> findAllDogs() {
        return dogRepository.findAll();
    }

    @QueryMapping
    public Dog findDogById(@Argument("id") Long id) {
//        Optional<String> optionalBreed = Optional.ofNullable(dogRepository.findBreedById(id));
//        String breed = optionalBreed.orElseThrow(DogNotFoundException::new);
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if (optionalDog.isPresent())
            return optionalDog.get();
        else
            throw new DogNotFoundException("Dog not found", id);
    }
}
