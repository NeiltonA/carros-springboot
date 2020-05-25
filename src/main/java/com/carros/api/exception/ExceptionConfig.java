package com.carros.api.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice //class permite interceptar alguns eventos de rest exclusive a exceção
public class ExceptionConfig extends ResponseEntityExceptionHandler{
// exclusive= se acontecer qualquer exception do tipo abaixo o metodo erroNotFound vai ser chamado
	@ExceptionHandler({
		//exception que que tratar, caso queira colocar mais de umaé só add , dps da class e a exception
		EmptyResultDataAccessException.class})
	public ResponseEntity erroNotFound(Exception ex) {
		return ResponseEntity.notFound().build();
	}
	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity erroBadRequest() {
		return ResponseEntity.badRequest().build();
	}

	
	@ExceptionHandler({AccessDeniedException.class})
	public ResponseEntity accessDenied() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionError(HttpStatus.FORBIDDEN.toString(), "Acesso negado"));
	}
	
 @Override
 //sobrescreve o handleHttpRequestMethodNotSupported retornando um ResponseEntity
protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {
	return new ResponseEntity<>(new ExceptionError(HttpStatus.METHOD_NOT_ALLOWED.toString(), "Operação não permitida"), HttpStatus.METHOD_NOT_ALLOWED);
	//retorna o objeto	"ExceptionError("Operação não permitida")" para o spring retornar um json
	}

	 
	 
 

}