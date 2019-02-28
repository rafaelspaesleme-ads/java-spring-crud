package br.com.avantews.resource.exception;

import javax.servlet.http.HttpServletRequest;

import br.com.avantews.services.exception.IntegridadeDeDadosException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.avantews.services.exception.ObjetoNaoEncontradoException;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjetoNaoEncontradoException e, HttpServletRequest request) {
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(IntegridadeDeDadosException.class)
	public ResponseEntity<StandardError> dateIntegrityException(IntegridadeDeDadosException e, HttpServletRequest request) {
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError error = new ValidationError(HttpStatus.NOT_FOUND.value(), "Erro de validação", LocalDateTime.now());
		for (FieldError x : e.getBindingResult().getFieldErrors()){
			error.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
