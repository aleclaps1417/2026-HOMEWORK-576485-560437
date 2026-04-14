package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;

class ComandoPrendiTest {
	
	private Partita partita;
	private ComandoPrendi comando;

	@BeforeEach
	public void setUp() throws Exception {
		this.partita=new Partita();
		this.comando=new ComandoPrendi();
		
		this.partita.getStanzaCorrente().addAttrezzo(new Attrezzo("martello",2));
	}

	@Test
	public void testComandoPrendiAttrezzoPresente() {
		this.comando.setParametro("martello");
		this.comando.esegui(this.partita);
		
		assertTrue("L'attrezzo martello ora dovrebbe trovarsi nella borsa",
				this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertFalse("L'attrezzo non dovrebbe piu stare nella stanza",this.partita.getStanzaCorrente().hasAttrezzo("martello"));
	}
	
	@Test
	public void testComandoPrendiAttrezzoNonPresente() {
		this.comando.setParametro("airpods");
		this.comando.esegui(this.partita);
		assertFalse("La borsa non dovrebbe cambiare",this.partita.getGiocatore().getBorsa().hasAttrezzo("airpods"));
		assertFalse("La borsa non dovrebbe cambiare",this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
	}
	
	@Test
	public void testComandoPrendiAttrezzoNull() {
		this.comando.setParametro(null);
		this.comando.esegui(this.partita);
		assertTrue("Gli attrezzi nella stanza non dovrebbero cambiare",this.partita.getStanzaCorrente().hasAttrezzo("martello"));
		assertFalse("La borsa non dovrebbe cambiare",this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
	}
	
	@Test
	public void testComandoPrendiBorsaPiena() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("chiodo",10));
		this.comando.setParametro("martello");
		this.comando.esegui(this.partita);
		assertTrue("Gli attrezzi nella stanza non dovrebbero cambiare",this.partita.getStanzaCorrente().hasAttrezzo("martello"));
		assertFalse("La borsa non dovrebbe cambiare",this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
	}


}
