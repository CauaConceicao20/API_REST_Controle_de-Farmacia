package com.remedios.cursoapi.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeExceptions {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> tratador404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> tratador400(MethodArgumentNotValidException ex) {
		var erro = ex.getFieldErrors();
		
		return ResponseEntity.badRequest().body(erro.stream().map(DadosErro::new).toList());
	}
	
	public record DadosErro(String mensagem, String field) {
		DadosErro(FieldError erro) {
			this(erro.getDefaultMessage(), erro.getField());
		}
	}
	
}
