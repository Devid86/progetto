package it.univpm.progetto.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import it.univpm.progetto.model.DropboxFile;
import it.univpm.progetto.model.Revisione;
import it.univpm.progetto.model.Statistics;

	
/**
 * Classe che contiene i metodi per ottenere le statistiche generali e sui singoli file
 * @author Devid
 * 
 */
public class StatisticResponse {
	
	public static ArrayList<Statistics> getSingleStats(ArrayList<DropboxFile> lista){
		ArrayList<Statistics> single = new ArrayList<Statistics>(); 
		Iterator<?> iterator = lista.iterator();
		while (iterator.hasNext()) {
			DropboxFile obj = new DropboxFile();
			Statistics file = new Statistics();
			obj = (DropboxFile)iterator.next();
			ArrayList<Revisione> rev = obj.getRevisions();
			file = RevisionResponse.getRevisionStats (rev);
			file.setLast_rev(rev.get(0).getRev_date());
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
		// All'interno del ciclo, letta l'estensione del file, inserisco una nuova chiave nell'HashMap se non presente
		// Incremento il valore legato alla chiave se invece è già contenuta nell'HashMap
		hash.put("File's Average Size (bytes)", getSize(lista));
		hash.put("Revision's Average Time (hours)", getTime(getSingleStats(lista)));
		hash.put("Files Revisions", RevisionResponse.getRevisions(lista));
		return hash;
	}

	/**
	 * Metodo che restituisce la dimensione media dei file contenuti nella cartella DropBox presa in esame
	 * @param lista
	 * @return
	 */
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
	
	private static int getTime(ArrayList<Statistics> lista) {
		Iterator<?> iterator = lista.iterator();
		float time = 0;
		while (iterator.hasNext()) {
			Statistics obj = new Statistics();
			obj = (Statistics)iterator.next();
			if (obj.getMid_time()!= null && obj.getMid_time().getUnit() == "hours") time = time + obj.getMid_time().getTempo_med();
		}
		return (int)time/lista.size();
	}

}
