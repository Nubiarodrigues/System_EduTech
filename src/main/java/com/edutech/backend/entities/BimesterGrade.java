package com.edutech.backend.entities;

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

    @OneToOne
    @JoinColumn(name = "student_id")
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


    public Double finalAverageCalculation(double grade1, double grade2, double grade3, double grade4, double gradeFinal) {
        double finalAverage = (grade1 + grade2 + grade3 + grade4) / 4.0;

        if(finalAverage > 7.0){
            System.out.println("O aluno está na final: " + finalAverage);
        } else {
            return finalAverage;
        }

        double finalGlobal = (finalAverage + gradeFinal) / 2.0;

        if(finalGlobal >= 7.0){
            System.out.println("Aluno aprovado: " + finalGlobal);
        }
        return finalGlobal;
    }
}
