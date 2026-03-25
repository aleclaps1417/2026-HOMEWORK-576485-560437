package it.uniroma3.diadia;


import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StanzaTest {
	private Stanza stanza;
	
	


	@BeforeEach
	public void setUp()  {
		this.stanza=new Stanza("atrio");
		
	}
	private Stanza creaStanzaConAttrezziGenerici(String nomeStanza,int numeroAttrezzi) {
		Stanza s=new Stanza(nomeStanza);
		for(int i=0;i<numeroAttrezzi;i++) {
			s.addAttrezzo(new Attrezzo("attrezzo"+i,1));
		}
		return s;
	}
	
	private Attrezzo attrezzo(String nome, int peso) {
		return new Attrezzo(nome,peso);
	}
	
	private Stanza creaStanzaConAdiacenze(String nomeStanza,String direzione,String nomeStanzaAdiacente) {
		Stanza base=new Stanza(nomeStanza);
		Stanza adiacente=new Stanza(nomeStanzaAdiacente);
		base.impostaStanzaAdiacente(direzione, adiacente);
		return base;
	}
	
	private Stanza creaStanzaConAttrezzi(String nomeStanza,String...nomiAttrezzi) {
		Stanza s=new Stanza(nomeStanza);
		for(String nome:nomiAttrezzi) {
			s.addAttrezzo(new Attrezzo(nome,1));
		}
		return s;
	}
	
	
	@Test
	public void testImpostaStanzaAdiacenteAssenti() {
		Stanza stanza1=new Stanza("cucina");
		this.stanza.impostaStanzaAdiacente("nord", stanza1);
		assertEquals(stanza1,this.stanza.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testImpostaStanzaAdiacenteSostituzione() {
		Stanza salone=new Stanza("salone");
		Stanza s=creaStanzaConAdiacenze("atrio","nord","cucina");
		s.impostaStanzaAdiacente("nord", salone);
		assertEquals(salone,s.getStanzaAdiacente("nord"));
	}
	@Test
	public void testImpostaStanzaAdiacenteTroppeDirezioni() {
		for(int i=0;i<4;i++) {
			Stanza generica=new Stanza("stanza"+i);
			this.stanza.impostaStanzaAdiacente("a"+i, generica);
		}
		Stanza diTroppo=new Stanza("lavatoio");
		stanza.impostaStanzaAdiacente("su", diTroppo);
		assertNotEquals(diTroppo,this.stanza.getStanzaAdiacente("su"));
	}
	
	@Test
	public void testGetNome() {
		assertEquals("atrio",this.stanza.getNome());
	}
	
	
	
	@Test
	public void testAddAttrezzoStanzaVuota() {
		assertTrue(this.stanza.addAttrezzo(attrezzo("martello",1)));
	}
	@Test
	public void testAddAttrezzoStanzaPiena() {
		Stanza piena=creaStanzaConAttrezziGenerici("salone",10);
		assertFalse(piena.addAttrezzo(attrezzo("extra",1)));
	}
	@Test
	public void testAddAttrezzoStanzaNonPienaENonVuota() {
		Stanza salone=creaStanzaConAttrezziGenerici("salone",5);
		assertTrue(salone.addAttrezzo(attrezzo("extra",1)));
	}
	@Test
	public void testAddAttrezzoNull() {
		assertFalse(this.stanza.addAttrezzo(null));
	}
	
	
	public void testGetAttrezzoPresente() {
		Stanza salone=creaStanzaConAttrezzi("salone","coltello","martello");
		Attrezzo risultato=salone.getAttrezzo("martello");
		assertEquals("martello",risultato.getNome());
	}
	@Test
	public void testGetAttrezzoNonPresente() {
		assertNull(this.stanza.getAttrezzo("martello"));
	}
	@Test
	public void testGetAttrezzoNull() {
		assertNull(this.stanza.getAttrezzo(null));
	}
	
	@Test
	public void testGetAttrezzoPresenteEConNull() {
		Stanza salone=creaStanzaConAttrezzi("salone","coltello",null,"forbice");
		assertEquals("forbice",salone.getAttrezzo("forbice").getNome());
	}
	
	
	@Test
	public void testHasAttrezzoPresente() {
		this.stanza.addAttrezzo(attrezzo("martello",1));
		assertTrue(this.stanza.hasAttrezzo("martello"));
	}
	@Test
	public void testHasAttrezzoNonPresente() {
		Stanza salone=creaStanzaConAttrezzi("salone","coltello","forbice");
		assertFalse(salone.hasAttrezzo("chiodo"));
	}
	@Test
	public void testHasAttrezzoNomeNull() {
		assertFalse(this.stanza.hasAttrezzo(null));
	}
	//GET ATTREZZI FATTO
	@Test
	public void testGetAttrezziStanzaVuota() {
		Attrezzo []arrayAttrezzi=this.stanza.getAttrezzi();
		assertNull(arrayAttrezzi[0]);
	}
	
	@Test
	public void testGetAttrezziStanzaConAttrezzo() {
		Stanza salone=creaStanzaConAttrezzi("salone","martello","coltello");
		Attrezzo []arrayAttrezzi=salone.getAttrezzi();
		assertEquals(salone.getAttrezzo("coltello"),arrayAttrezzi[1]);
	}
	
	@Test
	public void testGetAttrezziConNull() {
		Attrezzo []arrayAttrezzi=this.stanza.getAttrezzi();
		assertNull(arrayAttrezzi[0]);
	}
	
	@Test
	public void testToStringStanzaVuota() {
		String desc=this.stanza.toString();
		assertTrue(desc.contains("atrio"));
	}
	
	@Test
	public void tesToStringStanzaConAttrezzo() {
		Stanza salone=creaStanzaConAttrezzi("salone", "martello");
		String desc=salone.toString();
		assertTrue(desc.contains("martello"));
		;
		
	}
	
	@Test
	public void testToStringStanzaConStanzaAdiacente() {
		Stanza salone=creaStanzaConAdiacenze("salone","sud","cucina");
		String desc=salone.toString();
		assertTrue(desc.contains("sud"));
		
	}
	
	@Test
	public void testGetDescrizione() {
		assertEquals(this.stanza.toString(),this.stanza.getDescrizione());
	}
	
	
	@Test
	public void testGetDirezioniStanzaSenzaCollegamenti() {
		String []direzioni=this.stanza.getDirezioni();
		assertEquals(0,direzioni.length);
	}
	@Test
	public void testGetDirezioniStanzaCollegata() {
		Stanza salone=creaStanzaConAdiacenze("salone","nord","cucina");
		String[]direzioni=salone.getDirezioni();
		assertEquals(1,direzioni.length);
		assertEquals("nord",direzioni[0]);
	}
	
	@Test
	public void testGetDirezioniStanzaConTroppeDirezioni() {
		for(int i=0;i<5;i++) {
			Stanza generica =new Stanza("a"+i);
			this.stanza.impostaStanzaAdiacente("a"+i, generica);
		}
		String []direzioni=this.stanza.getDirezioni();
		assertEquals(4,direzioni.length);
		
	}
	
	@Test
	public void testRemoveAttrezzoPresente() {
		Stanza salone=creaStanzaConAttrezzi("salone","martello","coltello");
		assertTrue(salone.removeAttrezzo(salone.getAttrezzo("martello")));
		
	}
	
	@Test
	public void testRemoveAttrezzoNull() {
		assertFalse(this.stanza.removeAttrezzo(null));
	}
	
	@Test
	public void testRemoveAttrezzoNonPresente() {
		Stanza salone=creaStanzaConAttrezzi("salone","martello","coltello");
		assertFalse(salone.removeAttrezzo(salone.getAttrezzo("sega")));
	}
	@Test
	public void testRemoveAttrezzoConVerificaDellaScomparsa() {
		Stanza salone=creaStanzaConAttrezzi("salone","martello","coltello");
		salone.removeAttrezzo(salone.getAttrezzo("martello"));
		assertFalse(salone.hasAttrezzo("martello"));
	}
	@Test
	public void testRemoveAttrezzoSenzaNome() {
		Stanza salone=creaStanzaConAttrezzi("salone","martello","coltello",null);
		assertFalse(salone.removeAttrezzo(null));
	}
	@Test
	public void testRemoveAttrezzo_NonPresenteInStanzaPiena() {    
	    Stanza salone = creaStanzaConAttrezzi("salone","martello","coltello");  
	    boolean rimosso = salone.removeAttrezzo(new Attrezzo("chiave", 1));
	    assertFalse(rimosso);
	}
	
	
}
