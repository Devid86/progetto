package it.univpm.progetto.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.progetto.model.DropboxFile;
import it.univpm.progetto.model.Revisione;
import it.univpm.progetto.model.Statistics;
import it.univpm.progetto.util.DownloadJSON;
import it.univpm.progetto.util.ParseJSON;

public class ServiceResponse {

	/**
	 * Classe che contiene i metodi per gestire il parametro multimediale
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
		HashMap<String, Long> hash = new HashMap<String, Long>();
		hash = getGeneralStats (lista);
		ArrayList<Statistics> single = getSingleStats(lista);
		listaDiRitorno.add(hash);
		listaDiRitorno.add(single);
		return listaDiRitorno;
	}

	public static ArrayList<Statistics> getSingleStats(ArrayList<DropboxFile> lista){
		ArrayList<Statistics> single = new ArrayList<Statistics>(); 
		Iterator<?> iterator = lista.iterator();
		while (iterator.hasNext()) {
			DropboxFile obj = new DropboxFile();
			Statistics file = new Statistics();
			obj = (DropboxFile)iterator.next();
			ArrayList<Revisione> rev = obj.getRevisions();
			file = getRevisionStats (rev);
			file.setPath(obj.getPath());
			file.setSize(obj.getSize());
			single.add(file);
		}
		return single;
	}
	
	private static Statistics getRevisionStats(ArrayList<Revisione> rev) {
		Statistics stat = new Statistics();
		Iterator<?> iterator = rev.iterator();
		LocalDateTime max = rev.get(0).getRev_date();
		LocalDateTime min = rev.get(0).getRev_date();
		while (iterator.hasNext()) {
			Revisione obj = new Revisione();
			obj = (Revisione)iterator.next();
			stat.setNum_rev(stat.getNum_rev() + 1);
			min = obj.getRev_date();
		}
		Duration durata = Duration.between(min, max);
		if (!durata.isZero()) {
			stat.setMid_time(durata, stat.getNum_rev());
			stat.setRev_day(durata, stat.getNum_rev());
			stat.setRev_week(rev, min);
		}
		return stat;
	}
	
	private static HashMap<String, Long> getGeneralStats(ArrayList<DropboxFile> lista){
		HashMap<String, Long> hash = new HashMap<String, Long>();
		Iterator<?> iterator = lista.iterator();
		while (iterator.hasNext()) {
			DropboxFile obj = new DropboxFile();
			obj = (DropboxFile)iterator.next();
			String Est = obj.getMetadati().getEstensione();
			hash.putIfAbsent(Est, (long)1);
			hash.put(Est, hash.get(Est) + 1);
		}
		hash.put("File's Average Size", getSize(lista));
		hash.put("Files Revisions", getRevisions(lista));
		return hash;
	}

	private static long getSize(ArrayList<DropboxFile> lista) {
		Iterator<?> iterator = lista.iterator();
		long size = 0;
		while (iterator.hasNext()) {
			DropboxFile obj = new DropboxFile();
			obj = (DropboxFile)iterator.next();
			size = size + obj.getSize();
		}
		return size;
	}
		
	private static long getRevisions(ArrayList<DropboxFile> lista) {
		Iterator<?> iterator = lista.iterator();
		long rev = 0;
		while (iterator.hasNext()) {
			DropboxFile obj = new DropboxFile();
			obj = (DropboxFile)iterator.next();
			Statistics stat = getRevisionStats(obj.getRevisions());
			rev = rev + stat.getNum_rev();
		}
		return rev;
	}

}
