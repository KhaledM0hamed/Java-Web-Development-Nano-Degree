package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.exception.*;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.service.UserService;
import com.udacity.jdnd.course3.critter.service.ValidationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    PetService petService;
    @Autowired
    UserService userService;
    @Autowired
    ValidationService validationService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO)
            throws EmployeeNotFoundException, MissingDataException, PetNotFoundException
             {

        validationService.validatePOJOAttributesNotNullOrEmpty(scheduleDTO);
        Schedule schedule = scheduleService.findSchedule(scheduleDTO.getId()).orElseGet(Schedule::new);

        schedule.setDate(scheduleDTO.getDate());
        schedule.setEmployees(userService.findEmployees(scheduleDTO.getEmployeeIds()));
        schedule.setPets(petService.findPets(scheduleDTO.getPetIds()));
        schedule.setActivities(scheduleDTO.getActivities());

        schedule = scheduleService.save(schedule);

        return copyScheduleToDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.findAllSchedules();
        return copyScheduleToDTO(schedules);
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) throws PetNotFoundException {
        return copyScheduleToDTO(scheduleService.findSchedulesForPet(petId));
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) throws EmployeeNotFoundException {
        return copyScheduleToDTO(scheduleService.findSchedulesForEmployee(employeeId));
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) throws CustomerNotFoundException {
        return copyScheduleToDTO(scheduleService.findSchedulesForCustomer(customerId));
    }

    private List<ScheduleDTO> copyScheduleToDTO(List<Schedule> schedules) {
        return schedules
                .stream()
                .map(s -> { return copyScheduleToDTO(s); })
                .collect(Collectors.toList());
    }

    private ScheduleDTO copyScheduleToDTO(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, dto);
        schedule.getEmployees().forEach(employee -> {dto.getEmployeeIds().add(employee.getId());});
        schedule.getPets().forEach(pet -> {dto.getPetIds().add(pet.getId());});
        return dto;
    }
}
