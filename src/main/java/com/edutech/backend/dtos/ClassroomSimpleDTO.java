package com.edutech.backend.dtos;

import com.edutech.backend.entities.Classroom;

public record ClassroomSimpleDTO(
        Long id) {

    public ClassroomSimpleDTO(Classroom classroom){
        this(
          classroom.getId()
        );
    }
}
