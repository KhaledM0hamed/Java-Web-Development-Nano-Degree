package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.exception.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.exception.PetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;
    @Autowired
    CustomerRepository customerRepository;

    public Optional<Pet> findPet(Long id) {
        return petRepository.findById(id);
    }

    public List<Pet> findPetByOwner(Long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }

    public List<Pet> findPets(List<Long> petIds) {
        List<Pet> pets = petRepository.findAllById(petIds);

        if (petIds.size() != pets.size()) {
            List<Long> found = pets.stream().map(p -> p.getId()).collect(Collectors.toList());
            String missing = (String) petIds
                    .stream()
                    .filter( id -> !found.contains(id) )
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            throw new PetNotFoundException("Could not find pet(s) with id(s): " + missing);
        }
        return pets;
    }

    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    @Transactional
    public Pet save(Pet pet, Long ownerId) throws CustomerNotFoundException {
        Customer owner = customerRepository.findById(ownerId)
                .orElseThrow(() -> new CustomerNotFoundException("ID: " + ownerId));
        pet.setOwner(owner);
        pet = petRepository.save(pet);
        owner.getPets().add(pet);
        customerRepository.save(owner);

        return pet;
    }
}
