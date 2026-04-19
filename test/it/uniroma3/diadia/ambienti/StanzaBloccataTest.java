package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	private StanzaBloccata bloccata;
	private Stanza adiacente;

	@BeforeEach
	void setUp() throws Exception {
		this.bloccata=new StanzaBloccata("bloccata","nord","martello");
		this.adiacente=new Stanza("atrio");
		this.bloccata.impostaStanzaAdiacente("nord", this.adiacente);
	}

	@Test
	public void testGetStanzaAdiacenteConAttrezzo() {
		this.bloccata.addAttrezzo(new Attrezzo("martello",1));
		assertEquals("Avendo l'attrezzo che sblocca dovrebbe ritornarci la stanza adiacente",
				"atrio",this.bloccata.getStanzaAdiacente("nord").getNome());
	}
	
	@Test
	public void testGetStanzaAdiacentSenzaAttrezzo() {
		assertEquals("Non avendo l'attrezzo non dovrei ottenere la stanza adiacente",this.bloccata.getNome(), this.bloccata.getStanzaAdiacente("nord").getNome());
	}
	@Test
	public void testGetStanzaAdiacentDirezioneSbagliata() {
		assertNull("Non essendo sud il parametro e non avendo impostato una stanza adiacente a sud"
				+ "il risultato dovrebbe essere null",this.bloccata.getStanzaAdiacente("sud"));
	}
	@Test
	public void testToStringSenzaAttrezzo() {
		String risultato=this.bloccata.getDescrizione();	
		assertTrue("Non avendo aggiunto l'attrezzo la stanza è bloccata",risultato.contains("bloccata"));
	}
	@Test
	public void testToStringConAttrezzo() {
		this.bloccata.addAttrezzo(new Attrezzo("martello",1));
		String risultato=this.bloccata.getDescrizione();	
		assertTrue("Avendo aggiunto il martello la stampa dovrebbe essere quella generale",
				risultato.contains("martello"));
		assertFalse(risultato.contains("è bloccata"));
	}

}
