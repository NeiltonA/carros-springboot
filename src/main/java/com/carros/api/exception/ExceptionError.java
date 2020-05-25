package com.carros.api.exception;

import java.io.Serializable;

// cria um objeto precisar ser um Serializable para o srping transformar em json

public class ExceptionError implements Serializable {

	 private String error;
	 private String message;
	 //construtor
	 ExceptionError(String error, String message) {
		 this.error = error;
		 this.message = message;
	 }
	 
	 //get
	 public String getError() {
		 return error;
	 }
	 
	 public String getMessage() {
		 return message;
	 }
}

