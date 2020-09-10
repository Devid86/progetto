package it.univpm.progetto.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

/**
 * Classe per scaricare dall'indirizzo url il json contenente i dati
 * 
 * @author Devid
 *
 */
public class DownloadJSON {
	
	/**
	 * Metodo che definiisce un oggetto {@link HttpURLConnection} e avvia la connessione verso l'url {@link String} <b>uri</b> passsato come parametro
	 * @param uri	una {@link String} contentente l'url a cui connettersi
	 * @return {@link HttpURLConnection} connection
	 */
	private static HttpURLConnection apriConnessione(String uri) {
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) new URL(uri).openConnection();
			connection.setRequestMethod("POST"); //definisco la modalità di richiesta (POST)
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Authorization", "Bearer wyLKmgph90cAAAAAAAAAAQRnc0IwCF31KHurZ3mwivl7gzsbXSnHocCu6JpTzYzu"); //(imposto il token di accesso di dropbox)
			connection.addRequestProperty("User-Agent",	"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			connection.setDoOutput(true);
		} catch (MalformedURLException e) {
			System.out.println("L'url specificato è errato");
			e.printStackTrace();
		} catch (ProtocolException e) {
			System.out.println("Errore nel protocollo HTTP");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Connessione non riuscita");
			e.printStackTrace();
		}
		return connection;
	}
	/**
	 * Metodo che avvia la connesione al database di Dropbox mediante il link specificato in {@link String} <b>uri</b> utilizzando il metodo della API Dropbox "<b>list_folder</b>"
	 * @return {@link JSONObject} JSONObject json, oggetto contenente i dati in formato json scaricati dal server di Dropbox
	 */
	public static JSONObject downloadJSONFiles(){
		JSONObject jsonObject =new JSONObject();
		String uri="https://api.dropboxapi.com/2/files/list_folder";
		HttpURLConnection connection = null;
		//Definisco ed inizializzo l'oggetto HttpURLConnection, aprendo così la connessione verso l'uri specificato precedentemente ed imposto le relative proprietà
		connection = apriConnessione(uri);
		String bodyStringToSend = " {\"path\": " + "\""+ "/Applicazioni/AppEsameOOP" + "\""+ ", \"recursive\": " +true +"}"; //Definsico il path relativo alla cartella Dropbox da cui prendere i file
		sendBody(connection, bodyStringToSend);
		jsonObject = receiveJSONData(connection, jsonObject);
		connection.disconnect();
		return jsonObject;
	}

	/**
	 * Overload del metodo downloadJSONFiles che scarica i restanti file all'interno di eventuali sottocartelle indicate dal parametro {@link String} <b>cursor</b>utilizzando il metodo della API Dropbox "<b>list_folder/continue</b>"
	 * @param cursor	Cursore ricevuto dalla precedente chiamata all'API Dropbox (<b>list_folder</b> o <b>list_folder/continue</b>). Deve avere una lunghezza minima di 1
	 * @return {@link JSONObject} JSONObject json, oggetto contenente i dati in formato json scaricati dal server di Dropbox
	 */
	public static JSONObject downloadJSONFiles(String cursor){
		JSONObject jsonObject =new JSONObject();
		String uri="https://api.dropboxapi.com/2/files/list_folder/continue";
		HttpURLConnection connection = null;
		//Definisco ed inizializzo l'oggetto HttpURLConnection, aprendo così la connessione verso l'uri specificato precedentemente ed imposto le relative proprietà
		connection = apriConnessione(uri);
		String bodyStringToSend = " {\"cursor\": " + "\""+ cursor + "\"}"; //Definsico il path relativo alla cartella Dropbox da cui prendere i file
		sendBody(connection, bodyStringToSend);
		jsonObject = receiveJSONData(connection, jsonObject);
		connection.disconnect();
		return jsonObject;
	}

	/**
	 * Metodo che scarica in fromato json i metadati di uno specifico file indicando il suo percorso o in alternativa il suo id utilizzando il metodo della API Dropbox "<b>get_metadata</b>"
	 * @param filePathOrID, percorso o id del file
	 * @return {@link JSONObject} jsonObject, oggetto conenente i metadati di un file in formato json
	 */
	public static JSONObject downloadMetadata(String filePathOrID) {
		JSONObject jsonObject =new JSONObject();
		String uri="https://api.dropboxapi.com/2/files/get_metadata";
		HttpURLConnection connection = null;
		//Definisco ed inizializzo l'oggetto HttpURLConnection, aprendo così la connessione verso l'uri specificato precedentemente ed imposto le relative proprietà
		connection = apriConnessione(uri);
		String bodyStringToSend = " {\"path\": " + "\""+ filePathOrID + "\""+ ", \"include_media_info\": " +true +"}"; 
		sendBody(connection, bodyStringToSend );
		jsonObject = receiveJSONData(connection, jsonObject);
		connection.disconnect();
		return jsonObject;

	}

	/**
	 * Metodo che scarica in fromato json la lista delle revisioni di uno specifico file indicando il suo percorso o in alternativa il suo id utilizzando il metodo della API Dropbox "<b>list_revisions</b>"
	 * @param filePathOrID
	 * @return {@link JSONObject} jsonObject: oggetto conenente la lista delle revisioni di un file in formato json
	 */
	public static JSONObject downloadListRevisions(String filePathOrID) {
		JSONObject jsonObject =new JSONObject();
		String uri="https://api.dropboxapi.com/2/files/list_revisions";
		HttpURLConnection connection = null;
		connection = apriConnessione(uri);
		String bodyStringToSend = " {\"path\": " + "\""+ filePathOrID + "\"}"; 
		sendBody(connection, bodyStringToSend );
		jsonObject = receiveJSONData(connection, jsonObject);
		connection.disconnect();
		return jsonObject;

	}
	/**
	 * Metodo che serve ad inviare un body durante una richiesta
	 * instanzia l'oggetto {@link OutputStream} e ottengo il flusso in output utilizzando il metodo dell'oggetto {@link HttpURLConnection#getOutputStream()} connection
	 * @param connection		oggetto di tipo {@link HttpURLConnection}
	 * @param bodyStringToSend	{@link String} contenente il body da inviare contestualmente alla richiesta
	 * @throws IOException
	 */
	private static void sendBody(HttpURLConnection connection, String bodyStringToSend) {
		try {
			OutputStream output = connection.getOutputStream();
			byte[] path = bodyStringToSend.getBytes("utf-8");
			output.write(path);
			output.close();
		} catch (UnsupportedEncodingException e) {
			System.out.println("Codifica non supportata");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Invio body non riuscito");
			e.printStackTrace();
		}
	}

	/**
	 * Metodo che si occupa di scaricare, partendo da un oggetto {@link HttpURLConnection}, attraverso un flusso di input i dati, parsandoli in formato JSON
	 * @param connection	oggetto di tipo {@link HttpURLConnection}
	 * @param jsonObject	oggetto di tipo {@link JSONObject} in cui verranno salvati i dati scaricati
	 * @return {@link JSONObject} jsonObject
	 */
	private static JSONObject receiveJSONData(HttpURLConnection connection, JSONObject jsonObject) {
		String data = "";
		String line = "";

		
		InputStream inputStream = null;
		try {
			inputStream = connection.getInputStream();
		} catch (IOException e) {
			System.out.println("Errore nell'ottenere il flusso di input");
			e.printStackTrace();
		}
			

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		try {
			while((line=bufferedReader.readLine()) != null) {
				data += line; //leggo riga per riga ciò che ottengo e vado a "salvare" ogni riga all'interno della variabile data
			}
		} catch (IOException e) {
			System.out.println("Errore nella lettura dei dati");
			e.printStackTrace();
		}
			
		try {
			inputStream.close();
		} catch (IOException e1) {
			System.out.println("Errore nella chiusura del flusso di input");
			e1.printStackTrace();
		}
		try {
			jsonObject = (JSONObject)JSONValue.parseWithException(data); //effettuo il parsing della string data precedentemente popolata in un oggetto di tipo JSONObject
		} catch (ParseException e) {
			System.out.println("Errore nel parsing dei dati (String -> JSON) ");
			e.printStackTrace();
		} 
		return jsonObject;
	}
}
