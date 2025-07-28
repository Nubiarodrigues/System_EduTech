package com.edutech.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_FREQUENCY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Frequency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateRegistration;

    private Double percentagePresenceMonth;
    private Double percentageAbsenceMonth;

    private Integer classes = 6;

    private List<Integer> presenceDay = new ArrayList<>();
    private List<Integer> absenceDay = new ArrayList<>();

    private Double percentageDay;
    private Double totalPercentageDay;

    @ManyToOne
    @JoinColumn(name = "frequency_id")
    private Student student;

    private Long idSchool;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    public Double totalPercentage(Double percentage) {
       totalPercentageDay = this.percentageDay = percentage;
       return totalPercentageDay;
    }
}
