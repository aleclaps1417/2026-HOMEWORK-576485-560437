package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class ComandoPosaTest {

	private Partita partita;
	private Comando comando;
	private IO io;
	private Attrezzo attrezzo;
	Labirinto labirinto;
	
	

	@BeforeEach
	public void setUp() throws Exception {
		// 1. Usiamo il LabirintoBuilder per creare una base solida
		Labirinto lab = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.getLabirinto();
		this.partita = new Partita(lab);
		
		this.comando = new ComandoPosa();
		this.io = new IOConsole();
		
		// 2. Aggiungiamo il martello nella borsa
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("martello", 2));
	}
	


	@Test
	public void testPosaAttrezzoNull() {
		
		this.comando.setParametro(null);
		this.comando.esegui(this.partita,this.io);
		
		assertEquals("L'attrezzo dovrebbe ancora trovarsi nella borsa","martello",this.partita.getGiocatore().getBorsa().getAttrezzo("martello").getNome());
		
	}
	
	@Test
	public void testPosaAttrezzo() {
		this.comando.setParametro("martello");
		this.comando.esegui(this.partita,this.io);
		
		assertEquals("martello",this.partita.getStanzaCorrente().getAttrezzo("martello").getNome());
		assertFalse("se ha posato l'attrezzo non dovrebbe piu averlo nella borsa",this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
	}
	@Test
	public void testPosaAttrezzoNonPresenteInBorsa() {
		this.comando.setParametro("chiave");
		this.comando.esegui(this.partita,this.io);
		assertFalse("Non essendoci l'attrezzo nella borsa, non può essere posato",this.partita.getStanzaCorrente().hasAttrezzo("chiave"));
	}
	
	
	@Test
	public void testGetNome() {
		assertEquals("Il nome del comando deve essere posa","posa",this.comando.getNome());
	}
	
	@Test
	public void testSetParametroEGetParametro() {
		this.comando.setParametro("martello");
		assertEquals("Il parametro dovrebbe essere martello","martello",this.comando.getParametro());
	}



}
