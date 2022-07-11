package com.stationone.filme.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@RestControllerAdvice
public class ErrorExceptionHandler {
	   private final Logger log = LoggerFactory.getLogger(ErrorExceptionHandler.class);
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentTypeMismatchException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("Erro:","Falha ao realizar conversão !" + ex.getMessage());
		log.warn("Erro: " + ex.getMessage());
		return errors;
	} 	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public Map<String, String> DataIntegrityViolationException(DataIntegrityViolationException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("Falha", ex.getMessage());	
		log.warn("Erro: " + ex.getMessage());
		return errors;
	}
}
