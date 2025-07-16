package com.edutech.backend.utils;


import com.edutech.backend.entities.BimesterGrade;
import com.edutech.backend.enuns.SituationStudent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bulletin {

    private String nameStudent;
    private String serie;
    private String identifierSeries;
    private Integer schoolYear;
    private List<BimesterGrade> bimesters;
    private SituationStudent situation;
    private LocalDate issueDate;
    private String digitalSignature;
}
