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

/**
 * Classe che contiene i metodi per la gestione delle {@link Exception}
 * riscontrate nel {@link ControllerClass}
 * @author Devid
 *
 */
@ControllerAdvice
public class ExceptionsHandler {
	
	/**
	 * Metodo che gestisce la {@link Exception} per il {@link Param} multi della {@link Route}
	 * @param ex exception to handle
	 * @return
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<String> typehandle(MethodArgumentTypeMismatchException ex){
		return new ResponseEntity<String>("Parametro errato", HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Metodo che gestisce la {@link Exception} per il tipo di dato del {@link Param} multi
	 * @param ex exception to handle
	 * @return
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<String> paramhandle(MissingServletRequestParameterException ex){
		return new ResponseEntity<String>("Required boolean parameter 'multi' is not present", HttpStatus.BAD_REQUEST);
	}
	
}


