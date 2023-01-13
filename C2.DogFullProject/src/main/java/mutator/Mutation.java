package mutator;

import com.example.c2dog.entity.Dog;
import com.example.c2dog.exception.BreedNotFoundException;
import com.example.c2dog.exception.DogNotFoundException;
import com.example.c2dog.repository.DogRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Component
public class Mutation {

    private final DogRepository dogRepository;

    public Mutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @MutationMapping
    public Boolean deleteDogBreed(@Argument("breed") String breed) {
        Boolean deleted = false;
        Iterable<Dog> allDogs = dogRepository.findAll();
        for (Dog dog: allDogs){
            if (dog.getBreed().equals(breed)){
                dogRepository.delete(dog);
                deleted = true;
            }
        }
        if (!deleted)
            throw new BreedNotFoundException("Breed not found!", breed);

        return deleted;
    }

    @MutationMapping
    public Dog updateDogName(@Argument("newName") String newName, @Argument("id") Long id){
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if (optionalDog.isPresent()){
            Dog dog = optionalDog.get();
            dog.setName(newName);
            dogRepository.save(dog);
            return dog;
        } else {
            throw new DogNotFoundException("Dog not found", id);
        }
    }
}
