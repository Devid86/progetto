package it.univpm.progetto.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Devid
 *
 */
public class Revisione {
	private String rev_id;
	private LocalDateTime rev_date;

	public Revisione() {
		super();
	}
	/**
	 * @param rev_id
	 * @param rev_date
	 */
	public Revisione(String rev_id, LocalDateTime rev_date) {
		super();
		this.rev_id = rev_id;
		this.rev_date = rev_date;
	}
	/**
	 * @return the rev_id
	 */
	public String getRev_id() {
		return rev_id;
	}
	/**
	 * @param rev_id the rev_id to set
	 */
	public void setRev_id(String rev_id) {
		this.rev_id = rev_id;
	}
	/**
	 * @return the rev_date
	 */
	public LocalDateTime getRev_date() {
		return rev_date;
	}
	
	
	/**
	 * La data viene passata come un parametro di tipo {@link String}, e convertita successivamente in un oggetto di tipo {@link LocalDateTime}
	 * La stringa contenente la data deve essere del tipo descritto in @see {@link DateTimeFormatter#ISO_ZONED_DATE_TIME}: 
	 * @param rev_date
	 */
	public void setRev_date(String rev_date) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
		this.rev_date = LocalDateTime.parse(rev_date, formatter);
	}
}
