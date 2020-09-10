package it.univpm.progetto.model;

import java.time.Duration;

public class MidTime {
	
	private float tempo_med;
	private String unit;
	
	public MidTime() {
		this.tempo_med = 0;
		this.unit = "secs";
	}

	public float getTempo_med() {
		return tempo_med;
	}

	public void setTempo_med(float tempo_med, Duration durata) {
		this.tempo_med = tempo_med;
		if (durata.toMinutes()!=0) {
			this.tempo_med = tempo_med/60;
			this.unit = "mins";
		}
		if (durata.toHours()!=0) {
			this.tempo_med = tempo_med/3600;
			this.unit = "hours";
		}
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
}
