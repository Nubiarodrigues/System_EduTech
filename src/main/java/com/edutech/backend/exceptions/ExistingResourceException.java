package com.edutech.backend.exceptions;

public class ExistingResourceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ExistingResourceException(String msg) {
		super(msg);
	}

}
