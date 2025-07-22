package com.edutech.backend.dtos.schoolnotice;

import com.edutech.backend.dtos.classroom.ClassroomSimpleDTO;
import com.edutech.backend.entities.SchoolNotices;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record SchoolNoticesResponseDTO(
        Long id,
        String title,
        String message,
        LocalDateTime dateCreation,
        String author,
        List<ClassroomSimpleDTO> classrooms) {

        public SchoolNoticesResponseDTO(SchoolNotices schoolNotices){
            this(
                schoolNotices.getId(),
                schoolNotices.getTitle(),
                schoolNotices.getMessage(),
                schoolNotices.getDateCreation(),
                schoolNotices.getAuthor(),
                schoolNotices.getClassrooms() != null ? schoolNotices.getClassrooms().stream().map(ClassroomSimpleDTO::new).collect(Collectors.toList()) : null);
        }
}
