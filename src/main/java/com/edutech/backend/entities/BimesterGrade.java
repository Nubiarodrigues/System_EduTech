package com.edutech.backend.entities;

import com.edutech.backend.enuns.SituationStudent;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_BIMESTER_GRADE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BimesterGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    private Integer bimester;
    private Double grade1;
    private Double grade2;
    private Double grade3;
    private Double grade4;
    private Double gradeFinal;
    private Double averageFinal;
    private Double averageGlobal;
    private Long idSchool;

    @Enumerated(EnumType.STRING)
    private SituationStudent situation;


    public Double finalAverageCalculation() {
        double averageBimester = (grade1 + grade2 + grade3 + grade4) / 4.0;
        return averageBimester;
    }

    public Double finalGlobalCalculation() {
        double averageBimester = (grade1 + grade2 + grade3 + grade4) / 4.0;

        double finalGlobal = (averageBimester + gradeFinal) / 2.0;
        return finalGlobal;
    }


    public SituationStudent defineSituationNoFinal(BimesterGrade bimester) {

        if (finalAverageCalculation() >= 7.0) {
            bimester.setSituation(SituationStudent.APROVADO);
        } else if (finalAverageCalculation() >= 4.0 && finalAverageCalculation() <= 6.9) {
            bimester.setSituation(SituationStudent.FINAL);
        } else {
            bimester.setSituation(SituationStudent.REPROVADO);
        }

        return bimester.getSituation();

    }

    public SituationStudent defineSituationFinal(BimesterGrade bimester){

        if(finalGlobalCalculation() >= 5.0){
            bimester.setSituation(SituationStudent.APROVADO);
        } else {
            bimester.setSituation(SituationStudent.REPROVADO);
        }

        return bimester.getSituation();

    }
}
