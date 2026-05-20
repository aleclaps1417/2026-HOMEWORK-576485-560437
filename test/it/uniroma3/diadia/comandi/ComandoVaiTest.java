package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class ComandoVaiTest {

	private ComandoVai comando;
	private IO io;
	
	@BeforeEach
	void setUp() {
		this.comando = new ComandoVai();
		
		this.io = new IOSimulator(new ArrayList<>());
	}



	@Test
	void testVaiMonolocaleDirInesistente() {
		Labirinto monolocale = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.getLabirinto();
		Partita partita = new Partita(monolocale);
		
		comando.setParametro("sud");
		comando.esegui(partita, this.io); // Passiamo io direttamente qui!
		
		assertEquals("Atrio", partita.getStanzaCorrente().getNome());
	}



	@Test
	void testVai_Bilocale_DirezioneNull() {
		Labirinto bilocale = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addStanza("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		Partita partita = new Partita(bilocale);
		
		comando.setParametro(null);
		comando.esegui(partita, this.io);

		
		assertEquals("Atrio", partita.getStanzaCorrente().getNome());
	}

	@Test
	void testVai_Bilocale_VaiNord() {
		Labirinto bilocale = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addStanza("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		Partita partita = new Partita(bilocale);

		comando.setParametro("nord");
		comando.esegui(partita, this.io);

		
		assertEquals("Biblioteca", partita.getStanzaCorrente().getNome());
	}

	@Test
	void testVai_Bilocale_VaiNordFallito() {
		Labirinto bilocale = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addStanza("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		Partita partita = new Partita(bilocale);

		
		comando.setParametro("sud");
		comando.esegui(partita, this.io);

	
		assertNotEquals("Biblioteca", partita.getStanzaCorrente().getNome());
		assertEquals("Atrio", partita.getStanzaCorrente().getNome());
	}

	@Test
	void testCfuDiminuisce() {
		Labirinto bilocale = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addStanza("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		Partita partita = new Partita(bilocale);
		
		int cfuIniziali = partita.getGiocatore().getCfu();

		comando.setParametro("nord");
		comando.esegui(partita, this.io);

		
		assertEquals(cfuIniziali - 1, partita.getGiocatore().getCfu());
	}



	@Test
	void testVai_Trilocale_DuePassiConsecutivi() {
	
		Labirinto trilocale = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addStanza("Aula N11")
				.addAdiacenza("Atrio", "Aula N11", "est")
				.addStanza("Aula N10")
				.addAdiacenza("Aula N11", "Aula N10", "sud")
				.getLabirinto();
		Partita partita = new Partita(trilocale);


		comando.setParametro("est");
		comando.esegui(partita, this.io);
		assertEquals("Aula N11", partita.getStanzaCorrente().getNome(), "Primo passo verso est fallito");


		comando.setParametro("sud");
		comando.esegui(partita, this.io);
		assertEquals("Aula N10", partita.getStanzaCorrente().getNome(), "Secondo passo verso sud fallito");
	}
}