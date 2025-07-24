package com.edutech.backend.entities;

import com.edutech.backend.enuns.TeachingState;
import com.edutech.backend.enuns.TypeSchool;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "TB_SCHOOL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String telephone;
    private String cnpj;
    private String cep;
    private String address;

    @Enumerated(EnumType.STRING)
    private TeachingState modalities;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
    private List<Classroom> classrooms;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
    private List<Coordinator>  coordinators;

    private TypeSchool typeSchool;
    private List<ClassSchedule> classSchedule;
}
