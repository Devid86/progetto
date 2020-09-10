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
			stat.num_rev ++;
			min = obj.getRev_date();
		}
		Duration durata = Duration.between(min, max);
		if (!durata.isZero()) {
			stat.mid_time = getMedTime(durata, stat.num_rev);
			stat.rev_day = getRevDay(durata, stat.num_rev);
			stat.rev_week = getRevWeek (rev, min);
		}
		return stat;
	}
	
	private static MidTime getMedTime(Duration durata, int num) {
		MidTime medtime = new MidTime(); 
		medtime.setTempo_med((float)durata.getSeconds()/num, durata);
		return medtime;
	}
	
	private static float getRevDay(Duration durata, int num) {
		if(durata.toDays()!=0) return (float)num/durata.toDays();
		return num;
	}
	
	private static int getRevWeek(ArrayList<Revisione> rev, LocalDateTime min) {
		int cont = 0;
		Iterator<?> iterator = rev.iterator();
		while (iterator.hasNext()) {
			Revisione obj = new Revisione();
			obj = (Revisione)iterator.next();
			if (obj.getRev_date().isBefore(min.plusDays(7))) cont++;
		}
		return cont;
		
	}


}


