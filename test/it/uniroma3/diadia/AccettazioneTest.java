package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class AccettazioneTest {

	@Test
	public void testPartitaSempliceVittoriaImmediata() {
		
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		
		List<String> comandi = List.of("vai nord");
		IOSimulator simulatore = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(labirinto, simulatore);
		
		gioco.gioca();
		
		assertTrue(simulatore.getMessaggiDellaRiga(0).contains("Hai vinto!"), 
				"Andando a nord dovrei vincere immediatamente");
	}

	@Test
	public void testPartitaPerdentePerEsaurimentoCFU() {

		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addStanza("Corridoio")
				.addAdiacenza("Atrio", "Corridoio", "nord")
				.addAdiacenza("Corridoio", "Atrio", "sud")
				.getLabirinto();
		
		
		List<String> comandi = new ArrayList<>();
		for (int i = 0; i < 11; i++) {
			comandi.add("vai nord");
			comandi.add("vai sud");
		}
		
		IOSimulator simulatore = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(labirinto, simulatore);
		
		gioco.gioca();
		

				List<String> messaggiUltimoTurno = simulatore.getMessaggiDellaRiga(19);
				assertTrue(messaggiUltimoTurno.contains("Hai esaurito i CFU..."), 
						"Il giocatore avrebbe dovuto finire i CFU dopo 20 passi");
	}
	
	@Test
	public void testAumentareComplessitaConAttrezzi() {

		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("lanterna", 3)
				.addStanza("Ripostiglio")
				.addAdiacenza("Atrio", "Ripostiglio", "est")
				.getLabirinto();
		
		List<String> comandi = List.of("prendi lanterna", "vai est", "posa lanterna", "fine");
		
		IOSimulator simulatore = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(labirinto, simulatore);
		
		gioco.gioca();
		
		
		List<String> messaggiTurnoPosa = simulatore.getMessaggiDellaRiga(2);
		
		boolean erroreRilevato = false;
		for(String m : messaggiTurnoPosa) {
			if(m.contains("Non trovi") || m.contains("vuota")) {
				erroreRilevato = true;
			}
		}
		
		assertTrue(!erroreRilevato, "Il giocatore doveva posare la lanterna senza ricevere messaggi di errore");
	}
}