package it.univpm.progetto.model;

public class Metadata {
	private String estensione;
	private String tipoFile;

	/**
	 * Classe che descrive i Metadati di un {@link DropboxFile}
	 * @author Devid
	 */
	public Metadata() {
	}

	/**
	 * @param estensione
	 * @param tipoFile
	 */
	public Metadata(String estensione, String tipoFile) {
		this.estensione = estensione;
		this.tipoFile = tipoFile;
	}

	/**
	 * @return the estensione
	 */
	public String getEstensione() {
		return estensione;
	}

	/**
	 * @param nome del file da cui ricavare l'estensione
	 */
	public void setEstensione(String nomeFile) {
		int index = nomeFile.lastIndexOf(".");
		this.estensione = nomeFile.substring(index);
	}

	/**
	 * @return the tipoFile
	 */
	public String getTipoFile() {
		return tipoFile;
	}

	/**
	 * @param tipoFile the tipoFile to set
	 */
	public void setTipoFile(String tipoFile) {
		this.tipoFile = tipoFile;
	}

	@Override
	public String toString() {
		return "estensione=" + estensione + ", tipoFile=" + tipoFile;
	}



}
