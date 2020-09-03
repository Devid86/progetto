package it.univpm.progetto.util;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.progetto.model.DropboxFile;
import it.univpm.progetto.model.Metadata;
import it.univpm.progetto.model.Revisione;

/**
 * Classe che contiene metodi statici per parsare un {@link JSONObject} 
 * @author Devid
 *
 */
public class ParseJSON {
	/**
	 * Metodo statico privato che effettua il parsing degli elementi presenti all'interno del {@link JSONObject} jsonObject, inizializza un generico {@link DropboxFile} e lo aggiunge all' {@link ArrayList} passato come parametro
	 * @param jsonObject		{@link JSONObject} contenente i dati di cui effettuare il parsing
	 * @param listaFile		{@link ArrayList} dove aggiungere il file Dropbox creato alla fine del processo di parsing
	 */
	private static void getListFolder(JSONObject jsonObject, ArrayList<DropboxFile> listaFile) {
		JSONArray array = (JSONArray) jsonObject.get("entries");
		Iterator<?> iterator = array.iterator();
		while (iterator.hasNext()) {
			JSONObject obj = new JSONObject();
			DropboxFile file = new DropboxFile();
			obj = (JSONObject)iterator.next();
			if(!obj.get(".tag").equals("folder")) { //aggiungo alla listaFile solo i file, saltando gli oggetti di tipo folder (cartelle)
				file.setId((String)obj.get("id"));
				file.setPath((String)obj.get("path_lower"));
				file.setName((String)obj.get("name"));
				listaFile.add(file);
			}
		}
	}

	/**
	 * Metodo pubblico statico che restituisce una lista ({@link ArrayList}) dei file. Successivamente aggiunge ad ogni elemento della lista i relativi metadati e revisioni
	 * @return {@link ArrayList} listaFile: lista contenente tutti i file presenti all'interno della cartella Dropbox considerata
	 */
	public static ArrayList<DropboxFile> parseJSON(){
		JSONObject jsonObject = DownloadJSON.downloadJSONFiles();
		ArrayList<DropboxFile> listaFile = new ArrayList<DropboxFile>();
		getListFolder(jsonObject, listaFile);

		while((boolean) jsonObject.get("has_more").equals(true)) {
			String cursor = (String) jsonObject.get("cursor");
			jsonObject = DownloadJSON.downloadJSONFiles(cursor);
			getListFolder(jsonObject, listaFile);
		}
		//TODO:for da sostituire con un oggetto iteratore
		for(int i =0; i<listaFile.size();i++) {
			//getMetadataFromJson(listaFile.get(i).getId()); serviva per controllare l'output
			listaFile.get(i).setMetadati(getMetadataFromJson(listaFile.get(i).getId()));
			listaFile.get(i).setRevisions(getRevisionsFromJson(listaFile.get(i).getId()));
		}
		return listaFile;
	}

	/**
	 * Metodo statico privato che recupera i metadati di un file Dropbox utilizzando il metodo statico {@link DownloadJSON#downloadMetadata(String)}, e successivamente crea una istanza della classe {@link Metadata} 
	 * @param id  id dropbox del file
	 * @return {@link Metadata} metadata 	contenente il tipo di file e l'estensione
	 */
	private static Metadata getMetadataFromJson(String id) {
		JSONObject jsonObject = DownloadJSON.downloadMetadata(id);
		Metadata metadata = new Metadata();
		metadata.setEstensione((String) jsonObject.get("name"));
		//Se non contiene la chiave "media_info" mi restituirà null, allora metterò come tipo "file" (generico)
		if( jsonObject.get("media_info")== null) {
			metadata.setTipoFile("file");
		}
		else { //altrimenti devo andare a prendere l'oggetto "media_info", per prendere l'oggetto "metadata", per prendere la chiave ".tag"
			JSONObject media_info = (JSONObject) jsonObject.get("media_info");
			JSONObject metadato = (JSONObject) media_info.get("metadata");
			metadata.setTipoFile((String) metadato.get(".tag"));
		}
		return metadata;
	}

	/**
	 * Metodo statico privato che restituisce un {@link ArrayList} di {@link Revisione}
	 * @param id	 id del file Dropbox
	 * @return {@link ArrayList} of {@link Revisione}	 array list conentente tutte le revisioni di un file
	 */
	private static ArrayList<Revisione> getRevisionsFromJson(String id) {
		ArrayList<Revisione> history= new ArrayList<Revisione>();
		JSONObject jsonObject = DownloadJSON.downloadListRevisions(id);
		JSONArray array = (JSONArray) jsonObject.get("entries");
		Iterator<?> iterator = array.iterator();
		while (iterator.hasNext()) {
			JSONObject obj = new JSONObject();
			Revisione revisione = new Revisione();
			obj = (JSONObject)iterator.next();
			revisione.setRev_id((String)obj.get("rev"));
			revisione.setRev_date((String)obj.get("client_modified"));
			history.add(revisione);
		}
		return history;
	}

}
