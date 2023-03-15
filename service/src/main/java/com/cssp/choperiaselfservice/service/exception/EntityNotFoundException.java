package com.cssp.choperiaselfservice.service.exception;

public class EntityNotFoundException extends RuntimeException {

	public EntityNotFoundException(String razao) {
		super(razao);
	}

}
