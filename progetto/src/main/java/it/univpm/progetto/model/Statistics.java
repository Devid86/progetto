package it.univpm.progetto.model;

import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;


public class Statistics {

	private Path path; 
	private int num_rev;
	private MidTime mid_time;
	private float rev_day;
	private int rev_week;
	private long size;
	
	public Statistics() {
		super();
		this.path = null;
		this.num_rev = 0;
		this.rev_day = 0;
		this.rev_week = 0;
		this.size = 0;
	}
	
	
	public MidTime getMid_time() {
		return mid_time;
	}

	public void setMid_time(MidTime mid_time) {
		this.mid_time = mid_time;
	}
	
	public Path getPath() {
		return path;
	}
	public void setPath(Path path) {
		this.path = path;
	}
	public int getNum_rev() {
		return num_rev;
	}
	public void setNum_rev(int num_rev) {
		this.num_rev = num_rev;
	}
	public float getRev_day() {
		return rev_day;
	}
	public void setRev_day(float rev_day) {
		this.rev_day = rev_day;
	}
	public float getRev_week() {
		return rev_week;
	}
	public void setRev_week(int rev_week) {
		this.rev_week = rev_week;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	
	public void setMid_time(Duration durata, float num) {
		this.mid_time = new MidTime(durata, num); 
	}
	
	public void setRev_day(Duration durata, int num) {
		if(durata.toDays()!=0) this.rev_day = (float)num/durata.toDays();
		else this.rev_day = num;
	}
	
	public void setRev_week (ArrayList<Revisione> rev, LocalDateTime min) {
		int cont = 0;
		Iterator<?> iterator = rev.iterator();
		while (iterator.hasNext()) {
			Revisione obj = new Revisione();
			obj = (Revisione)iterator.next();
			if (obj.getRev_date().isBefore(min.plusDays(7))) cont++;
		}
		this.rev_week = cont;
	}

}


