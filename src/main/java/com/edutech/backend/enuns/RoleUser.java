package com.edutech.backend.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleUser {

	ADMIN("admin"),
	TEACHER("teacher"),
	OPERATOR("operator"),
	STUDENT("student"),
	COORDINATOR("coordinator");

	private String role;

}
