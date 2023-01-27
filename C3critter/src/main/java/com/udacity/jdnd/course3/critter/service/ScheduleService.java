package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.exception.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.exception.EmployeeNotFoundException;
import com.udacity.jdnd.course3.critter.exception.PetNotFoundException;
import com.udacity.jdnd.course3.critter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    UserService userService;
    @Autowired
    PetService petService;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PetRepository petRepository;
    @Autowired
    CustomerRepository customerRepository;

    public Optional<Schedule> findSchedule(Long id) {
        return scheduleRepository.findById(id);
    }

    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Transactional
    public Schedule save(Schedule schedule)
            throws PetNotFoundException, EmployeeNotFoundException {

        schedule = scheduleRepository.save(schedule);

        for (Employee employee : schedule.getEmployees()){
            employee.getSchedules().add(schedule);
            employeeRepository.save(employee);
        }
        for (Pet pet : schedule.getPets()) {
            pet.getSchedules().add(schedule);
            petRepository.save(pet);
        }

        return schedule;
    }

    public List<Schedule> findSchedulesForPet(long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new PetNotFoundException("ID: " + petId));
        return pet.getSchedules();
    }

    public List<Schedule> findSchedulesForEmployee(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("ID: " + employeeId));
        return employee.getSchedules();
    }

    public List<Schedule> findSchedulesForCustomer(long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("ID: " + customerId));
        List<Schedule> customerSchedules = customer.getPets()
                .stream()
                .map(Pet::getSchedules)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return customerSchedules;
    }
}
