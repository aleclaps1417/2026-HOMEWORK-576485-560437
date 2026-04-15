package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;

class ComandoPrendiTest {
	
	private Partita partita;
	private ComandoPrendi comando;
	private IO io;

	@BeforeEach
	public void setUp() throws Exception {
		this.partita=new Partita();
		this.comando=new ComandoPrendi();
		
		this.partita.getStanzaCorrente().addAttrezzo(new Attrezzo("martello",2));
		this.io=new IOConsole();
	}

	@Test
	public void testComandoPrendiAttrezzoPresente() {
		this.comando.setParametro("martello");
		this.comando.esegui(this.partita,this.io);
		
		assertTrue("L'attrezzo martello ora dovrebbe trovarsi nella borsa",
				this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertFalse("L'attrezzo non dovrebbe piu stare nella stanza",this.partita.getStanzaCorrente().hasAttrezzo("martello"));
	}
	
	@Test
	public void testComandoPrendiAttrezzoNonPresente() {
		this.comando.setParametro("airpods");
		this.comando.esegui(this.partita,this.io);
		assertFalse("La borsa non dovrebbe cambiare",this.partita.getGiocatore().getBorsa().hasAttrezzo("airpods"));
		assertFalse("La borsa non dovrebbe cambiare",this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
	}
	
	@Test
	public void testComandoPrendiAttrezzoNull() {
		this.comando.setParametro(null);
		this.comando.esegui(this.partita,this.io);
		assertTrue("Gli attrezzi nella stanza non dovrebbero cambiare",this.partita.getStanzaCorrente().hasAttrezzo("martello"));
		assertFalse("La borsa non dovrebbe cambiare",this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
	}
	
	@Test
	public void testComandoPrendiBorsaPiena() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("chiodo",10));
		this.comando.setParametro("martello");
		this.comando.esegui(this.partita,this.io);
		assertTrue("Gli attrezzi nella stanza non dovrebbero cambiare",this.partita.getStanzaCorrente().hasAttrezzo("martello"));
		assertFalse("La borsa non dovrebbe cambiare",this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
	}
	
	@Test
	public void testGetNome() {
		assertEquals("Il nome del comando dovrebbe essere prendi","prendi", this.comando.getNome());
	}
	@Test
	public void testSetEGetParametro() {
		this.comando.setParametro("martello");
		assertEquals("Il nome del parametro dovrebbe essere martello ","martello",this.comando.getParametro());
	}


}
