package com.edutech.backend.exceptions;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(
				Instant.now(),
				status.value(),
				error,
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(
				Instant.now(),
				status.value(),
				error,
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> handleValidationException(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
				.collect(Collectors.toList());
		String errorMessage = String.join(", ", errors);

		StandardError err = new StandardError(Instant.now(), status.value(), "Validation Error", errorMessage,
				request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handleInvalidEnum(HttpMessageNotReadableException ex) {
		if (ex.getCause() instanceof InvalidFormatException) {
			return ResponseEntity.badRequest().body("Valor inválido ou vazio para Enum. Verifique os dados enviados");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Requisição inválida. Verifique os dados enviados.");
	}

	@ExceptionHandler(ExistingResourceException.class)
	public ResponseEntity<StandardError> existingResource(ExistingResourceException e, HttpServletRequest request) {
		String error = "Existing resource";
		HttpStatus status = HttpStatus.CONFLICT;
		StandardError err = new StandardError(
				Instant.now(),
				status.value(),
				error,
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ExternalServiceException.class)
	public ResponseEntity<StandardError> externalService(ExternalServiceException e, HttpServletRequest request){
		String error = "External service";
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		StandardError err = new StandardError(
				Instant.now(),
				status.value(),
				error,
				e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
