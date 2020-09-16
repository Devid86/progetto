package it.univpm.progetto.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import it.univpm.progetto.model.DropboxFile;
import it.univpm.progetto.model.Statistics;
import it.univpm.progetto.util.ParseJSON;

public class ServiceResponse {

	/**
	 * Classe che contiene i metodi per ottenere un {@link ArrayList} filtrato,
	 * attraverso il parametro multimediale, ed unire le statistiche relative
	 * ai file in esso contenuto a quelle generali  
	 * @author Devid
	 * 
	 */

	static ArrayList<DropboxFile> listaFile = ParseJSON.parseJSON();

	public static ArrayList<DropboxFile> filterArrayList(boolean check){
		ArrayList<DropboxFile> listaTroncata = new ArrayList<DropboxFile>();
		Iterator<?> iterator = listaFile.iterator();
		while (iterator.hasNext()) {
			DropboxFile file = (DropboxFile)iterator.next();
			if (check == file.isMultimediale()) {
				listaTroncata.add(file);
			}
		}
		return listaTroncata; 
	}

	public static List<Object> getStats(ArrayList<DropboxFile> lista){
		ArrayList<Object> listaDiRitorno = new ArrayList<Object>() ;
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		hash = StatisticResponse.getGeneralStats (lista);
		ArrayList<Statistics> single = StatisticResponse.getSingleStats(lista);
		listaDiRitorno.add(hash);
		listaDiRitorno.add(single);
		return listaDiRitorno;
	}

}
