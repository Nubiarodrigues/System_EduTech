package com.edutech.backend.enuns;

public enum RoleUser {

	ADMIN("admin"),
	TEACHER("teacher"),
	OPERATOR("operator"),
	STUDENT("student"),
	COORDINATION("coordination");

	private String role;

	RoleUser(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

}
