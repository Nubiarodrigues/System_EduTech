package com.edutech.backend.specifications;

import com.edutech.backend.entities.Classroom;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

public class ClassroomSpecifications  {

    @And({
            @Spec(path = "schoolYear", spec = Equal.class),
            @Spec(path = "shift", spec = Equal.class),
            @Spec(path = "modality", spec = Like.class)
    })

    public interface ClassroomSpecification extends Specification<Classroom>{}
}
