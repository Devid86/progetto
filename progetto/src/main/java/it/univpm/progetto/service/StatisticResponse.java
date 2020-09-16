package it.univpm.progetto.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import it.univpm.progetto.model.DropboxFile;
import it.univpm.progetto.model.Revisione;
import it.univpm.progetto.model.Statistics;

public class StatisticResponse {
	
	/**
	 * Classe che contiene i metodi per ottenere le statistiche generali e sui singoli file
	 * @author Devid
	 * 
	 */

	public static ArrayList<Statistics> getSingleStats(ArrayList<DropboxFile> lista){
		ArrayList<Statistics> single = new ArrayList<Statistics>(); 
		Iterator<?> iterator = lista.iterator();
		while (iterator.hasNext()) {
			DropboxFile obj = new DropboxFile();
			Statistics file = new Statistics();
			obj = (DropboxFile)iterator.next();
			ArrayList<Revisione> rev = obj.getRevisions();
			file = RevisionResponse.getRevisionStats (rev);
			file.setPath(obj.getPath());
			file.setSize(obj.getSize());
			single.add(file);
		}
		return single;
	}

	public static HashMap<String, Integer> getGeneralStats(ArrayList<DropboxFile> lista){
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		Iterator<?> iterator = lista.iterator();
		while (iterator.hasNext()) {
			DropboxFile obj = new DropboxFile();
			obj = (DropboxFile)iterator.next();
			String Est = obj.getMetadati().getEstensione();
			hash.putIfAbsent(Est, 0);
			hash.put(Est, hash.get(Est) + 1);
		}
		hash.put("File's Average Size", getSize(lista));
		hash.put("Files Revisions", RevisionResponse.getRevisions(lista));
		return hash;
	}

	private static int getSize(ArrayList<DropboxFile> lista) {
		Iterator<?> iterator = lista.iterator();
		long size = 0;
		while (iterator.hasNext()) {
			DropboxFile obj = new DropboxFile();
			obj = (DropboxFile)iterator.next();
			size = size + obj.getSize();
		}
		return (int)size/lista.size();
	}

}
