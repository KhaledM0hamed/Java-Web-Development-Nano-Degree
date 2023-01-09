package com.udacity.DogRestApi.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udacity.DogRestApi.entity.Dog;
import com.udacity.DogRestApi.repository.DogRepository;
import com.udacity.DogRestApi.service.BreedNotFoundException;
import com.udacity.DogRestApi.service.DogNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    private DogRepository dogRepository;

    public Mutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Boolean deleteDogBreed(String breed) {
        Boolean deleted = false;
        Iterable<Dog> allDogs = dogRepository.findAll();
        //Loop through all dogs to check their breed!
        for (Dog dog: allDogs){
            if (dog.getBreed().equals(breed)){
                dogRepository.delete(dog);
                deleted = true;
            }
        }
        //throw an exception if the breed doesn't exist!
        if (!deleted)
            throw new BreedNotFoundException("Breed Not Found!", breed);

        return deleted;
    }

    public Dog updateDogName(String newName, Long id){
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if(optionalDog.isPresent()){
            Dog dog = optionalDog.get();
            //set the new name and save the updated dog
            dog.setName(newName);
            dogRepository.save(dog);
            return dog;
        } else {
            throw new DogNotFoundException("Dog not found!", id);
        }
    }
}
