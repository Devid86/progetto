package it.univpm.progetto.controller;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import it.univpm.progetto.model.DropboxFile;
import it.univpm.progetto.service.ServiceResponse;
import it.univpm.progetto.util.DownloadJSON;

/**
 * Classe che specificla le rotte che possono essere richieste al server
 * @author Devid
 *
 */
@RestController
public class ControllerClass {

	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public ResponseEntity<?> getAllData(
			@RequestParam(name="multi", required = true) boolean param) {
		return new ResponseEntity<ArrayList<DropboxFile>>(ServiceResponse.filterArrayList(param), HttpStatus.OK);
	}


	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	public ResponseEntity<?> getStats(
			@RequestParam(name="multi", required = true) boolean param) {
		ArrayList<DropboxFile> lista = ServiceResponse.filterArrayList(param);
		return new ResponseEntity<Object>(ServiceResponse.getStats(lista), HttpStatus.OK);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<String> handle(MethodArgumentTypeMismatchException ex){
		return new ResponseEntity<String>("Parametro errato", HttpStatus.BAD_REQUEST);
	}
	
}
