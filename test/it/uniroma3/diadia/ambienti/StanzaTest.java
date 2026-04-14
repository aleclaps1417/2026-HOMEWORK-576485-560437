package it.uniroma3.diadia.ambienti;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

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
	public void testImpostaStanzaAdiacente() {
		Stanza cucina=new Stanza("cucina");
		this.stanza.impostaStanzaAdiacente("nord", cucina);
		assertEquals(cucina,this.stanza.getStanzaAdiacente("nord"),"La stanza adiacente a nord dovrebbe essere la cucina");
	}
	
	@Test
	public void testImpostaStanzaAdiacenteSostituzione() {
		Stanza salone=new Stanza("salone");
		Stanza atrio=creaStanzaConAdiacenze("atrio","nord","cucina");
		atrio.impostaStanzaAdiacente("nord", salone);
		assertEquals(salone,atrio.getStanzaAdiacente("nord"),"Il salone dovrebbe aver sostituito la cucina a nord");
	}
	@Test
	public void testImpostaStanzaAdiacenteTroppeDirezioni() {
		for(int i=0;i<4;i++) {
			Stanza generica=new Stanza("stanza"+i);
			this.stanza.impostaStanzaAdiacente("a"+i, generica);
		}
		Stanza diTroppo=new Stanza("lavatoio");
		stanza.impostaStanzaAdiacente("su", diTroppo);
		assertNotEquals(diTroppo,this.stanza.getStanzaAdiacente("su"),
				"Non dovrebbe essere possibile aggiungere più di 4 direzioni");
	}
	
	@Test
	public void testGetNome() {
		assertEquals("atrio",this.stanza.getNome(),"Il nome della stanza dovrebbe essere atrio");
	}
	
	
	
	@Test
	public void testAddAttrezzoStanzaVuota() {
		assertTrue(this.stanza.addAttrezzo(attrezzo("martello",1)),
				"Dovrebbe essere possibile aggiungere un attrezzo in una stanza vuota");
	}
	@Test
	public void testAddAttrezzoStanzaPiena() {
		assertFalse(creaStanzaConAttrezziGenerici("salone",10).addAttrezzo(attrezzo("extra",1)),
				"Non dovrebbe essere possibile aggiungere attrezzi oltre il limite di 10");
	}
	@Test
	public void testAddAttrezzoStanzaNonPienaENonVuota() {
		assertTrue(creaStanzaConAttrezziGenerici("salone",5).addAttrezzo(attrezzo("extra",1)),
				"Dovrebbe essere possibile aggiungere un attrezzo se la stanza non è ancora piena");
	}
	@Test
	public void testAddAttrezzoNull() {
		assertFalse(this.stanza.addAttrezzo(null),"L'aggiunta di un attrezzo null deve restituire false");
	}
	
	@Test
	public void testGetAttrezzoPresente() {
		Stanza salone=creaStanzaConAttrezzi("salone","coltello","martello");
		Attrezzo risultato=salone.getAttrezzo("martello");
		assertEquals("martello",risultato.getNome(),"Dovrebbe restituire l'attrezzo martello se presente");
	}
	@Test
	public void testGetAttrezzoNonPresente() {
		assertNull(this.stanza.getAttrezzo("martello"),()->"Dovrebbe restituire null se l'attrezzo non esiste nella stanza");
	}
	@Test
	public void testGetAttrezzoNull() {
		assertNull(this.stanza.getAttrezzo(null),"getAttrezzo con parametro null deve restituire null");
	}
	
	@Test
	public void testGetAttrezzoPresenteEConNull() {
		Stanza salone=creaStanzaConAttrezzi("salone","coltello",null,"forbice");
		assertEquals("forbice",salone.getAttrezzo("forbice").getNome(),
				"Il metodo deve ignorare i valori null nell'array e trovare l'attrezzo richiesto");
	}
	
	
	@Test
	public void testHasAttrezzoPresente() {
		this.stanza.addAttrezzo(attrezzo("martello",1));
		assertTrue(this.stanza.hasAttrezzo("martello"),"hasAttrezzo dovrebbe confermare la presenza del martello");
	}
	@Test
	public void testHasAttrezzoNonPresente() {
		Stanza salone=creaStanzaConAttrezzi("salone","coltello","forbice");
		assertFalse(salone.hasAttrezzo("chiodo"),"hasAttrezzo deve restituire false se l'attrezzo non è presente nella stanza");
	}
	@Test
	public void testHasAttrezzoNomeNull() {
		assertFalse(this.stanza.hasAttrezzo(null),"hasAttrezzo con parametro null deve restituire false");
	}
	//GET ATTREZZI FATTO
	@Test
	public void testGetAttrezziStanzaVuota() {
		Attrezzo []arrayAttrezzi=this.stanza.getAttrezzi();
		assertNull(arrayAttrezzi[0],()->"In una stanza vuota, il primo elemento dell'array attrezzi deve essere null");
	}
	
	@Test
	public void testGetAttrezziStanzaConAttrezzo() {
		Stanza salone=creaStanzaConAttrezzi("salone","martello","coltello");
		Attrezzo []arrayAttrezzi=salone.getAttrezzi();
		assertEquals(salone.getAttrezzo("coltello"),arrayAttrezzi[1],
				"L'array restituito deve contenere gli attrezzi nelle posizioni corrette");
	}
	
	
	@Test
	public void testGetAttrezziDopoRimozione() {
	    Attrezzo martello = new Attrezzo("martello", 2);
	    this.stanza.addAttrezzo(martello);
	    this.stanza.removeAttrezzo(martello); 
	    
	    assertNull(this.stanza.getAttrezzi()[0], () -> "Dopo la rimozione dell'unico attrezzo, il primo slot deve tornare null");
	}
	
	@Test
	public void testToStringStanzaVuota() {
		String desc=this.stanza.toString();
		assertTrue(desc.contains("atrio"),"La descrizione della stanza deve contenere almeno il suo nome");
	}
	
	@Test
	public void tesToStringStanzaConAttrezzo() {
		Stanza salone=creaStanzaConAttrezzi("salone", "martello");
		String desc=salone.toString();
		assertTrue(desc.contains("martello"),"La descrizione della stanza deve elencare gli attrezzi presenti");
		
	}
	
	@Test
	public void testToStringStanzaConStanzaAdiacente() {
		assertTrue(creaStanzaConAdiacenze("salone","sud","cucina").toString().contains("sud"),
				"La descrizione della stanza deve indicare le direzioni d'uscita disponibili");
		
	}
	
	@Test
	public void testGetDescrizione() {
		assertEquals(this.stanza.toString(),this.stanza.getDescrizione(),
				"getDescrizione deve restituire la stessa stringa prodotta da toString");
	}
	
	
	@Test
	public void testGetDirezioniStanzaSenzaCollegamenti() {
		String []direzioni=this.stanza.getDirezioni();
		assertEquals(0,direzioni.length,"Una stanza senza adiacenze deve restituire un array di direzioni vuoto");
	}
	@Test
	public void testGetDirezioniStanzaCollegata() {
		assertEquals(1,creaStanzaConAdiacenze("salone","nord","cucina").getDirezioni().length,"Dovrebbe esserci esattamente una direzione disponibile");
		assertEquals("nord",creaStanzaConAdiacenze("salone","nord","cucina").getDirezioni()[0],"La direzione disponibile dovrebbe essere 'nord'");
	}
	
	@Test
	public void testGetDirezioniStanzaConTroppeDirezioni() {
		for(int i=0;i<5;i++) {
			Stanza generica =new Stanza("a"+i);
			this.stanza.impostaStanzaAdiacente("a"+i, generica);
		}
		String []direzioni=this.stanza.getDirezioni();
		assertEquals(4,direzioni.length,"Il numero massimo di direzioni memorizzabili deve essere 4");
		
	}
	
	@Test
	public void testRemoveAttrezzoPresente() {
		assertTrue(creaStanzaConAttrezzi("salone","martello","coltello").removeAttrezzo(creaStanzaConAttrezzi("salone","martello","coltello").getAttrezzo("martello")));
		
	}
	
	@Test
	public void testRemoveAttrezzoNull() {
		assertFalse(this.stanza.removeAttrezzo(null),"Il tentativo di rimuovere un attrezzo null deve restituire false");
	}
	
	@Test
	public void testRemoveAttrezzoNonPresente() {
		assertFalse(creaStanzaConAttrezzi("salone","martello","coltello").removeAttrezzo(creaStanzaConAttrezzi("salone","martello","coltello").getAttrezzo("sega")),
				"La rimozione di un attrezzo non presente deve restituire false");
	}
	@Test
	public void testRemoveAttrezzoConVerificaDellaScomparsa() {
		Stanza salone=creaStanzaConAttrezzi("salone","martello","coltello");
		salone.removeAttrezzo(salone.getAttrezzo("martello"));
		assertFalse(salone.hasAttrezzo("martello"));
	}
	@Test
	public void testRemoveAttrezzoSenzaNome() {
		assertFalse(creaStanzaConAttrezzi("salone","martello","coltello",null).removeAttrezzo(null));
	}
	@Test
	public void testRemoveAttrezzoNonPresenteInStanzaConAttrezzi() {    
	    Stanza salone = creaStanzaConAttrezzi("salone","martello","coltello");  
	    boolean rimosso = salone.removeAttrezzo(new Attrezzo("chiave", 1));
	    assertFalse(rimosso,"Il metodo deve restituire false se l'attrezzo con quel nome non esiste nella stanza");
	}
	
	
}
