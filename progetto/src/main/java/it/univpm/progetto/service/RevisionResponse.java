package it.univpm.progetto.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import it.univpm.progetto.model.DropboxFile;
import it.univpm.progetto.model.Revisione;
import it.univpm.progetto.model.Statistics;

/**
 * Classe che contiene i metodi per ottenere le statistiche, sia generali che singole,
 * in maniera specifica sulle sole revisioni
 * @author Devid
 * 
 */
public class RevisionResponse {

	public static Statistics getRevisionStats(ArrayList<Revisione> rev) {
		Statistics stat = new Statistics();
		Iterator<?> iterator = rev.iterator();
		LocalDateTime max = rev.get(0).getRev_date();
		// Inizializzo la data di ultima revisione col primo elemento dell'ArrayList
		LocalDateTime min = rev.get(0).getRev_date();
		// Mi assicuro che venga valorizzata anche la prima revisione nel caso fosse unica
		
		while (iterator.hasNext()) {
			Revisione obj = new Revisione();
			obj = (Revisione)iterator.next();
			stat.setNum_rev(stat.getNum_rev() + 1);
			min = obj.getRev_date();
		}
		Duration durata = Duration.between(min, max);
		// Per evitare una division by 0, controllo che esistano almeno due revisioni
		if (!durata.isZero()) {
			stat.setMid_time(durata, (float)durata.getSeconds()/stat.getNum_rev());
			stat.setRev_day(durata, stat.getNum_rev());
			stat.setRev_week(rev, min);
		}
		return stat;
	}

	public static int getRevisions(ArrayList<DropboxFile> lista) {
		Iterator<?> iterator = lista.iterator();
		int rev = 0;
		while (iterator.hasNext()) {
			DropboxFile obj = new DropboxFile();
			obj = (DropboxFile)iterator.next();
			Statistics stat = getRevisionStats(obj.getRevisions());
			rev = rev + stat.getNum_rev();
		}
		return rev;
	}

}
