package it.univpm.progetto.model;

import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Classe che descrive gli attributi e i metodi delle statistiche per un singolo file
 * @author Devid
 *
 */
public class Statistics {
	
	private Path path; 
	private int num_rev;
	private MidTime mid_time;
	private float rev_day;
	private int rev_week;
	private long size;
	private LocalDateTime last_rev;
	
	/**
	 * @param path
	 * @param num_rev
	 * @param mid_time
	 * @param rev_day
	 * @param rev_week
	 * @param size
	 * @param last_rev
	 */
	public Statistics() {
		super();
		this.path = null;
		this.num_rev = 0;
		this.rev_day = 0;
		this.rev_week = 0;
		this.size = 0;
		this.last_rev = null;
	}
	
	/**
	 * @return the mid_time
	 */
	public MidTime getMid_time() {
		return mid_time;
	}

	/**
	 * @param mid_time the mid_time to set
	 */
	public void setMid_time(MidTime mid_time) {
		this.mid_time = mid_time;
	}
	
	/** 
	 * @return the path
	 */
	public Path getPath() {
		return path;
	}
	
	/**
	 * @param path the path to set
	 */
	public void setPath(Path path) {
		this.path = path;
	}
	
	/** 
	 * @return the num_rev
	 */
	public int getNum_rev() {
		return num_rev;
	}
	
	/**
	 * @param num_rev the num_rev to set
	 */
	public void setNum_rev(int num_rev) {
		this.num_rev = num_rev;
	}
	
	/** 
	 * @return the rev_day
	 */
	public float getRev_day() {
		return rev_day;
	}
	
	/**
	 * @param rev_day the rev_day to set
	 */
	public void setRev_day(float rev_day) {
		this.rev_day = rev_day;
	}
	
	/** 
	 * @return the rev_week
	 */
	public float getRev_week() {
		return rev_week;
	}
	
	/**
	 * @param rev_week the rev_week to set
	 */
	public void setRev_week(int rev_week) {
		this.rev_week = rev_week;
	}
	
	/** 
	 * @return the size
	 */
	public long getSize() {
		return size;
	}
	
	/**
	 * @param size the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}
	
	/**
	 * @return the last_rev
	 */
	public LocalDateTime getLast_rev() {
		return last_rev;
	}

	/**
	 * @param ultima_rev the ultima_rev to set
	 */
	public void setLast_rev(LocalDateTime last_rev) {
		this.last_rev = last_rev;
	}
	
	/**
	 * Overload di setMid_time
	 * @param durata @param num the duration between the revisions in different types
	 */
	public void setMid_time(Duration durata, float num) {
		this.mid_time = new MidTime(durata, num); 
		// Utilizzando un overload del costruttore della classe MidTime
		// viene effettuato il setting dell'attributo mid_time
	}
	
	
	/**
	 * Overload di setRev_day
	 * @param durata the duration between the revisions
	 * @param num the revision's number
	 */
	public void setRev_day(Duration durata, int num) {
		if(durata.toDays()!=0) this.rev_day = (float)num/durata.toDays();
		else this.rev_day = num;
		// Se il tempo tra le revisioni supera il giorno allora divido le revisioni per i giorni
		// Altrimenti le revisioni per giorno corrispondono al totale delle revisioni
	}
	
	/**
	 * Overload di setRev_week
	 * @param rev the {@link ArrayList} of a single file's revisions
	 * @param min the first revision of a single file
	 */
	public void setRev_week (ArrayList<Revisione> rev, LocalDateTime min) {
		int cont = 0;
		Iterator<?> iterator = rev.iterator();
		// Scorro l'ArrayList alla ricerca delle revisioni in una settimana
		while (iterator.hasNext()) {
			Revisione obj = new Revisione();
			obj = (Revisione)iterator.next();
			if (obj.getRev_date().isBefore(min.plusDays(7))) cont++;
		}
		// Se la revisione ha una data compresa nei 7 giorni successivi alla prima revisione incremento il contatore
		this.rev_week = cont;
	}

}