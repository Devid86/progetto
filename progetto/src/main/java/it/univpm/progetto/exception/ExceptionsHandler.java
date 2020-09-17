package it.univpm.progetto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.RouteMatcher.Route;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.tags.Param;
import it.univpm.progetto.controller.ControllerClass;


@ControllerAdvice
public class ExceptionsHandler {
	
	/**
	 * Metodi che gestiscono le {@link Exception} riscontrate nel {@link ControllerClass}
	 * al livello di {@link Param} e {@link Type} presenti nella {@link Route}
	 * @param ex
	 */
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<String> typehandle(MethodArgumentTypeMismatchException ex){
		return new ResponseEntity<String>("Parametro errato", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<String> paramhandle(MissingServletRequestParameterException ex){
		return new ResponseEntity<String>("Required boolean parameter 'multi' is not present", HttpStatus.BAD_REQUEST);
	}
	
}


