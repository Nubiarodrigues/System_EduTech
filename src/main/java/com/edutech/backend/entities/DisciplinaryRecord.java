package com.edutech.backend.entities;

import com.edutech.backend.enuns.TypeOccurrence;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "TB_DISCIPLINARY_RECORD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DisciplinaryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDate date;

    private String description;
    private String measures;
    private String responsible;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;

    @Enumerated(EnumType.STRING)
    private TypeOccurrence occurrence;

    private Integer verbalWarnings = 0;
    private Integer warningsIssued = 0;
    private Integer suspensionsIssued = 0;
    private Boolean expulsion = false;
}
