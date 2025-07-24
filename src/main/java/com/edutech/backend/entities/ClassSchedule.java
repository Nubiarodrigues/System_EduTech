package com.edutech.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassSchedule {

    private String dayWeek;
    private List<Discipline> disciplines;
    private LocalTime hours;
    private Integer classesTeach;
}
