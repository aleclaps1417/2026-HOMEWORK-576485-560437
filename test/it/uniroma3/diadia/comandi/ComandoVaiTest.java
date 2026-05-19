package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {

	private Partita partita;
	private ComandoVai comando;
	private Stanza stanzaPartenza;
	private Stanza stanzaArrivo;
	private IO io;
	
	@BeforeEach
	public void setUp() {
		// Usiamo il LabirintoBuilder invece di creare le stanze a mano
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Stanza partenza")
				.addStanza("Stanza arrivo")
				.addAdiacenza("Stanza partenza", "Stanza arrivo", "nord")
				.getLabirinto();

		// Passiamo il labirinto alla partita (addio errore del costruttore!)
		this.partita = new Partita(labirinto); 
		this.comando = new ComandoVai();
		this.io = new IOConsole();

		// Recuperiamo i riferimenti alle stanze create dal Builder
		this.stanzaPartenza = labirinto.getStanzaIniziale();
		this.stanzaArrivo = this.stanzaPartenza.getStanzaAdiacente("nord");
	}
	
	@Test
	public void testEseguiDirezioneValida() {
		this.comando.setParametro("nord");
		this.comando.esegui(this.partita, this.io);
		
		assertEquals(this.stanzaArrivo, this.partita.getStanzaCorrente());
		assertEquals(19, this.partita.getGiocatore().getCfu(), "Il giocatore dovrebbe avere un cfu in meno(parte da 20)");
	}
	
	@Test
	public void testEseguiDirezioneInesistente() {
		this.comando.setParametro("bubu");
		this.comando.esegui(this.partita, this.io);
		
		assertEquals(this.stanzaPartenza, this.partita.getStanzaCorrente(), "Il giocatore non dovrebbe essersi mosso");
		assertEquals(20, this.partita.getGiocatore().getCfu(), "I cfu non dovrebbero essere diminuiti(20 di default)");
	}
	
	@Test
	public void testEseguiDirezioneNulla() {
		this.comando.setParametro(null);
		this.comando.esegui(this.partita, this.io);
		
		assertEquals(this.stanzaPartenza, this.partita.getStanzaCorrente(), "Il giocatore non dovrebbe essersi mosso");
		assertEquals(20, this.partita.getGiocatore().getCfu(), "I cfu non dovrebbero essere diminuiti(20 di default)");
	}
	
	@Test
	public void testGetNome() {
		assertEquals("vai", this.comando.getNome(), "Il nome del comando deve essere vai");
	}
	
	@Test
	public void testGetParamentro() {
		this.comando.setParametro("nord");
		assertEquals("nord", this.comando.getParametro(), "Il parametro dovrebbe essere nord");
	}
}