package it.univpm.progetto.model;

import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Classe che descrive gli attributi e i metodi di un singolo file all'interno di Dropbox
 * @author Devid
 *
 */
public class DropboxFile extends File{

	private String id;
	private Metadata metadati;
	private ArrayList<Revisione> revisions;

	public DropboxFile() {
		super();
	}

	/**
	 * @param name
	 * @param path
	 * @param id
	 * @param metadati
	 */
	public DropboxFile(String name, Path path, String id, Metadata metadati) {
		super(name, path);
		this.id = id;
		this.metadati = metadati;
		this.revisions = null;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the metadati
	 */
	public Metadata getMetadati() {
		return metadati;
	}

	/**
	 * @param metadati the metadati to set
	 */
	public void setMetadati(Metadata metadati) {
		this.metadati = metadati;
	}

	/**
	 * @return the revisions
	 */
	public ArrayList<Revisione> getRevisions() {
		return revisions;
	}

	/**
	 * @param revisions the revisions to set
	 */
	public void setRevisions(ArrayList<Revisione> revisions) {
		this.revisions = revisions;
	}

	/**
	 * @return true se il file è di tipo multimediale 
	 */
	@Override
	public boolean isMultimediale() {
		// TODO definire quando il file è multimediale (confronto tra estensione o tipo??)
		return false;
	}

	@Override
	public String toString() {
		return super.toString()+ "id= " + id + ", metadati= " + metadati;
	}

}
