package com.edutech.backend.entities;

import com.edutech.backend.entities.embeddable.Address;
import com.edutech.backend.enuns.TeachingStage;
import com.edutech.backend.enuns.TypeSchool;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

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
    private Integer capacity;

    @Embedded
    private Address address;

    @ElementCollection(targetClass = TeachingStage.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "school_teaching_stage", joinColumns = @JoinColumn(name = "school_id"))
    @Column(name = "teaching_stage")
    private Set<TeachingStage> stages;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
    private List<Classroom> classrooms;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
    private List<Coordinator>  coordinators;

    @Enumerated(EnumType.STRING)
    private TypeSchool typeSchool;
}
