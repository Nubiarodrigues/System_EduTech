package com.edutech.backend.entities;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.edutech.backend.enuns.RoleUser;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
public abstract class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	protected Long id;

	protected String name;
	protected String email;
	protected String password;
	protected String registration;

	@Enumerated(EnumType.STRING)
	protected RoleUser role;

	public User(String name) {
		this.name = name;
	}

	public User(Long id) {
		this.id = id;
	}

}
