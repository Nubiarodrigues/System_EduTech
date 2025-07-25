package com.edutech.backend.dtos.student;

import com.edutech.backend.entities.Student;

public record StudentSimpleResponseDTO(String name, String registration) {

    public StudentSimpleResponseDTO(Student student) {
        this(student.getName(), student.getRegistration());
    }

}
