package it.univpm.progetto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProgettoApplication {

		/**
		 * Metodo main che, tramite spring, lancia il server
		 * il quale resta in ascolto per eventuali richieste   
		 * @param args
		 */
	
	public static void main(String[] args) {
		SpringApplication.run(ProgettoApplication.class, args);
	}

}
