package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class GiocatoreTest {
	private Giocatore giocatore;
	
	
	@BeforeEach
	public void setUp()  {
		this.giocatore=new Giocatore();
		
	}
	private Giocatore creaGiocatoreConBorsaConUnAttrezzo(String nomeAttrezzo,int peso) {
		Attrezzo martello=new Attrezzo(nomeAttrezzo,peso);
		this.giocatore.getBorsa().addAttrezzo(martello);
		return this.giocatore;
	}
	
	@Test
	public void testGetCfu() {
		//All'inizio della partita sono previsti 20CFU
		assertEquals(20,this.giocatore.getCfu(),"Ad inizio partita dovresti avere 20cfu");
	}
	
	@Test
	public void testSetCfu() {
		this.giocatore.setCfu(3);
		assertEquals(3,this.giocatore.getCfu(),"I CFU dovrebbero essere stati aggiornati a 3");
	}
	@Test
	public void testGetBorsaVuota() {
		assertNotNull(this.giocatore.getBorsa());
		assertTrue(this.giocatore.getBorsa().isEmpty(), "La borsa del giocatore dovrebbe essere inizialmente vuota");
	}
	@Test
	public void testgetBorsaConAttrezzo() {
		this.giocatore=creaGiocatoreConBorsaConUnAttrezzo("martello",2);
		assertNotNull(this.giocatore.getBorsa());
		assertTrue(this.giocatore.getBorsa().hasAttrezzo("martello"),"La borsa dovrebbe contenere il martello appena aggiunto");
	}

}
