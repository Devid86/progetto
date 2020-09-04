package it.univpm.progetto.service;

import java.util.ArrayList;
import java.util.Iterator;

import it.univpm.progetto.model.DropboxFile;
import it.univpm.progetto.util.ParseJSON;

public class ServiceResponse {
	
/**
 * Classe che contiene i metodi per gestire il parametro multimediale
 * @author Devid
 * 
 */
	
	static ArrayList<DropboxFile> listaFile = new ArrayList<DropboxFile>();
	
	public static ArrayList<DropboxFile> checkParameter (int param){
		listaFile = ParseJSON.parseJSON();
		if (param != 0) listaFile = cutArrayList (param); 
		return listaFile;
	}
	
	public static ArrayList<DropboxFile> cutArrayList (int param){
		ArrayList<DropboxFile> listaTroncata = new ArrayList<DropboxFile>();
		Iterator<?> iterator = listaFile.iterator();
		while (iterator.hasNext()) {
			DropboxFile file = (DropboxFile)iterator.next();
			if ((param == 1) && (file.isMultimediale())) {					
				listaTroncata.add(file);
			}
			if ((param == 2) && (!file.isMultimediale())) {					
				listaTroncata.add(file);
			}
		}
	return listaTroncata; 
	}
}
