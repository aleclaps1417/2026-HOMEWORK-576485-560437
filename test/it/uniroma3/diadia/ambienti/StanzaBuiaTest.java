package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	StanzaBuia caverna;
	

	@BeforeEach
	void setUp() throws Exception {
		this.caverna=new StanzaBuia("caverna","lanterna");
	}

	@Test
	public void testGetDescrizioneAttrezzoPresente() {
		caverna.addAttrezzo(new Attrezzo("lanterna",1));
		String risultato=caverna.getDescrizione();
		assertTrue(risultato.contains("lanterna"),"La stanza contiene la lanterna quindi dovrebbe restituire la descrizione");
	}
	
	@Test
	public void testGetDescrizioneAttrezzoNonPresente() {
		String risultato=caverna.getDescrizione();
		assertTrue(risultato.contains("buio pesto"),"Non avendo la lanterna, la descrizione dovrebbe risultare buia");
	}
	

}
