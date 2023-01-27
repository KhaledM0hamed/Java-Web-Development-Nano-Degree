package com.udacity.jdnd.course3.critter.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="employee")
public class Employee extends User {

    @ElementCollection
    @CollectionTable(
            name="employee_skill",
            joinColumns = @JoinColumn(name="id"), uniqueConstraints = @UniqueConstraint(columnNames = {"ID", "SKILL"}))
    @Column(name="skill")
    private Set<EmployeeSkill> skills;

    @ElementCollection
    @CollectionTable(
            name="day_of_week",
            joinColumns = @JoinColumn(name="id"), uniqueConstraints = @UniqueConstraint(columnNames = {"ID", "DAY"}))
    @Column(name="day")
    private Set<DayOfWeek> daysAvailable;

    @ManyToMany(
            mappedBy = "employees")
    @JsonManagedReference
    @JsonIgnoreProperties("schedules")
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<Schedule> schedules = new ArrayList<>();
}
