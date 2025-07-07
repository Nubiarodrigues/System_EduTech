package com.edutech.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "TB_ADMINISTRATOR")
@Getter
@Setter
@SuperBuilder
public class Administrator extends User {

    public Administrator() {}

}
