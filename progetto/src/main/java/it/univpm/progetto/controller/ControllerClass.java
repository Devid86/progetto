package it.univpm.progetto.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progetto.model.DropboxFile;
import it.univpm.progetto.service.ServiceResponse;

/**
 * Classe che specificla le rotte che possono essere richieste al server
 * @author Devid
 *
 */
@RestController
public class ControllerClass {

	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public ResponseEntity<?> getAllData(
			@RequestParam(name="multi", required = false, defaultValue = "0") int param) {
		if (param<0||param>2)
			return new ResponseEntity<String>("Il parametro deve essere compreso tra 0 e 2", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<ArrayList<DropboxFile>>(ServiceResponse.checkParameter(param), HttpStatus.OK);
	}


}
