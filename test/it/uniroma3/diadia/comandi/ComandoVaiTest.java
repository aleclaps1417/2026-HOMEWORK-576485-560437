package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.ComandoVai;

class ComandoVaiTest {

	private Partita partita;
	private ComandoVai comando;
	private Stanza stanzaPartenza;
	private Stanza stanzaArrivo;
	private IO io;
	
	@BeforeEach
	public void setUp() {
		this.partita=new Partita();
		this.comando=new ComandoVai();
		this.stanzaArrivo=new Stanza("Stanza arrivo");
		this.stanzaPartenza=new Stanza("Stanza partenza");
		
		this.stanzaPartenza.impostaStanzaAdiacente("nord", stanzaArrivo);
		this.partita.setStanzaCorrente(this.stanzaPartenza);
		this.io=new IOConsole();
	}
	
	@Test
	public void testEseguiDirezioneValida() {
		this.comando.setParametro("nord");
		this.comando.esegui(this.partita,this.io);
		assertEquals(
			this.stanzaArrivo,	this.partita.getStanzaCorrente());
		assertEquals("Il giocatore dovrebbe avere un cfu in meno(parte da 20)",19,this.partita.getGiocatore().getCfu());
	}
	
	@Test
	public void testEseguiDirezioneInesistente() {
		this.comando.setParametro("bubu");
		this.comando.esegui(this.partita,this.io);
		
		assertEquals("Il giocatore non dovrebbe essersi mosso",
				this.stanzaPartenza,this.partita.getStanzaCorrente());
		assertEquals("I cfu non dovrebbero essere diminuiti(20 di default)",
				20,this.partita.getGiocatore().getCfu());
	}
	
	@Test
	public void testEseguiDirezioneNulla() {
		this.comando.setParametro(null);
		this.comando.esegui(this.partita,this.io);
		assertEquals("Il giocatore non dovrebbe essersi mosso",
				this.stanzaPartenza,this.partita.getStanzaCorrente());
		assertEquals("I cfu non dovrebbero essere diminuiti(20 di default)",
				20,this.partita.getGiocatore().getCfu());
	}
	
	@Test
	public void testGetNome() {
		assertEquals("Il nome del comando deve essere vai","vai",this.comando.getNome());
	}
	@Test
	public void testGetParamentro() {
		this.comando.setParametro("nord");
		assertEquals("Il nome del parametro dovrebb essere nord","nord",this.comando.getParametro());
		
	}
}
