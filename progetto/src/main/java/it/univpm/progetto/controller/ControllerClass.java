package it.univpm.progetto.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progetto.model.DropboxFile;
import it.univpm.progetto.util.ParseJSON;

/**
 * Classe che specificla le rotte che possono essere richieste al server
 * @author Devid
 *
 */
@RestController
public class ControllerClass {
	
	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<DropboxFile>> getAllData() {
		return new ResponseEntity<ArrayList<DropboxFile>>(ParseJSON.parseJSON(), HttpStatus.OK);
	}
	
	
}
