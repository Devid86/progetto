package it.univpm.progetto.model;

import java.time.Duration;

public class MidTime {
	
	private float tempo_med;
	private String unit;
	
	/**
	 * @param tempo_med
	 * @param unit
	 */
	public MidTime() {
		this.tempo_med = 0;
		this.unit = "secs";
	}
		
	/**
	 * @param tempo_med tempo medio revisioni
	 * @param unit unità di misura del tempo medio
	 */

	public MidTime(Duration durata, float num) {
		// Tempo medio tra le revisioni, espresso in secondi
		this.tempo_med = num;
		this.unit = "secs";
		// Se il tempo medio supera il minuto, divido per 60 ed aggiorno l'unità di misura
		if (durata.toMinutes()!=0) {
			this.tempo_med = num/60;
			this.unit = "mins";
		}
		// Se il tempo medio supera l'ora, divido per 3600 ed aggiorno l'unità di misura
		if (durata.toHours()!=0) {
			this.tempo_med = num/3600;
			this.unit = "hours";
		}
	}

	/**
	 * @return the tempo_med
	 */
	
	public float getTempo_med() {
		return tempo_med;
	}
	
	/**
	 * @param tempo_med tempo medio tra una revisione e l'altra
	 */
	
	public void setTempo_med(float tempo_med) {
		this.tempo_med = tempo_med;
	}
	
	
	/**
	 * @return the unit
	 */

	public String getUnit() {
		return unit;
	}
	
	/**
	 * @param unit unità di misura del tempo medio
	 */

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
}
