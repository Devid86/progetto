package it.univpm.progetto.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import it.univpm.progetto.model.DropboxFile;
import it.univpm.progetto.model.Statistics;
import it.univpm.progetto.util.ParseJSON;


/**
 * Classe che contiene i metodi per ottenere un {@link ArrayList} filtrato,
 * attraverso il parametro multimediale, ed unire le statistiche relative
 * ai file in esso contenuto a quelle generali  
 * @author Devid
 * 
 */
public class ServiceResponse {
	
	static ArrayList<DropboxFile> listaFile = ParseJSON.parseJSON();

	public static ArrayList<DropboxFile> filterArrayList(boolean check){
		ArrayList<DropboxFile> listaTroncata = new ArrayList<DropboxFile>();
		Iterator<?> iterator = listaFile.iterator();
		while (iterator.hasNext()) {
			DropboxFile file = (DropboxFile)iterator.next();
			if (check == file.isMultimediale()) {
				listaTroncata.add(file);
			}
			// Se i parametri, del file e richiesto, sono concordi aggiungo il file
		}
		return listaTroncata; 
	}

	public static List<Object> getStats(ArrayList<DropboxFile> lista){
		ArrayList<Object> listaDiRitorno = new ArrayList<Object>() ;
		ArrayList<Statistics> single = StatisticResponse.getSingleStats(lista);
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		hash = StatisticResponse.getGeneralStats(lista);
		listaDiRitorno.add(hash);
		listaDiRitorno.add(single);
		// Unisco in ArrayList di Object l'HashMap delle statistiche generali con l'ArrayList
		// delle statistiche per singolo file e lo restituisco al Controller
		return listaDiRitorno;
	}

}
