package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;

class ComandoPosaTest {

	private Partita partita;
	private ComandoPosa comando;
	
	

	@BeforeEach
	public void setUp() throws Exception {
		this.partita=new Partita();
		
		this.comando=new ComandoPosa();
		
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("martello",2));
		
	}
	
	
	private void riempiStanza() {
		Attrezzo []a=new Attrezzo[10];
		for(int i=0;i<a.length;i++) {
			a[i]=new Attrezzo("a"+i,1);
		}
		for(int i=0;i<10;i++) {
			this.partita.getStanzaCorrente().addAttrezzo(a[i]);
		}
	}

	@Test
	public void testPosaAttrezzoNull() {
		
		this.comando.setParametro(null);
		this.comando.esegui(this.partita);
		
		assertEquals("L'attrezzo dovrebbe ancora trovarsi nella borsa","martello",this.partita.getGiocatore().getBorsa().getAttrezzo("martello").getNome());
		
	}
	
	@Test
	public void testPosaAttrezzo() {
		this.comando.setParametro("martello");
		this.comando.esegui(this.partita);
		
		assertEquals("martello",this.partita.getStanzaCorrente().getAttrezzo("martello").getNome());
		assertFalse("se ha posato l'attrezzo non dovrebbe piu averlo nella borsa",this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
	}
	@Test
	public void testPosaAttrezzoNonPresenteInBorsa() {
		this.comando.setParametro("chiave");
		this.comando.esegui(this.partita);
		assertFalse("Non essendoci l'attrezzo nella borsa, non può essere posato",this.partita.getStanzaCorrente().hasAttrezzo("chiave"));
	}
	
	@Test
	public void testPosaAttrezzoStanzaPiena() {
		riempiStanza();
		this.comando.setParametro("martello");
		this.comando.esegui(this.partita);
		
		assertEquals("L'attrezzo dovrebbe rimanere nella borsa","martello",this.partita.getGiocatore().getBorsa().getAttrezzo("martello").getNome());
		assertFalse("L'attrezzo non dovrebbe trovarsi nella stanza poiche non posato",this.partita.getStanzaCorrente().hasAttrezzo("martello"));
	}



}
