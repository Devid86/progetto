package it.univpm.progetto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@ControllerAdvice
public class ExceptionsHandler {
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<String> typehandle(MethodArgumentTypeMismatchException ex){
		return new ResponseEntity<String>("Parametro errato", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<String> paramhandle(MissingServletRequestParameterException ex){
		return new ResponseEntity<String>("Required boolean parameter 'multi' is not present", HttpStatus.BAD_REQUEST);
	}
	
}


