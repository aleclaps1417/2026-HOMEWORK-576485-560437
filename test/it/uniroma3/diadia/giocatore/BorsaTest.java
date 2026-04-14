package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {
	private Borsa borsa;
	
	@BeforeEach
	public void setUp() {
		this.borsa=new Borsa();
	}
	
	private Borsa creaBorsaConUnAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzo=new Attrezzo(nomeAttrezzo,3);
		this.borsa.addAttrezzo(attrezzo);
		return this.borsa;
	}
	
	private Borsa creaBorsaConPiuAttrezzi(String...nomeAttrezzo) {
		for(String nome: nomeAttrezzo) {
			this.borsa.addAttrezzo(new Attrezzo(nome,0));
		}
		return this.borsa;
	}
	private Borsa creaBorsaConPesoMax(String nomeAttezzo) {
		Attrezzo attrezzo=new Attrezzo(nomeAttezzo,10);
		this.borsa.addAttrezzo(attrezzo);
		return this.borsa;
	}
	
	
	@Test
	public void testGetAttrezzoPresente() {
		this.borsa=creaBorsaConUnAttrezzo("martello");
		assertEquals("martello",this.borsa.getAttrezzo("martello").getNome(),"Dovrebbe restituire l'attrezzo con il nome cercato se presente");
	}
	@Test
	public void testGetAttrezzoBorsaVuota() {
		assertNull(this.borsa.getAttrezzo("martello"),() -> "In una borsa vuota getAttrezzo deve sempre restituire null");
	}
	@Test
	public void testGetAttrezzoNonPresente() {
		this.borsa=creaBorsaConUnAttrezzo("martello");
		assertNull(this.borsa.getAttrezzo("chiodo"),()->"Dovrebbe restituire null se l'attrezzo cercato non è in borsa");
	}
	
	
	@Test
	public void testIsEmptyBorsaVuota() {
		assertTrue(this.borsa.isEmpty(),"Una borsa appena creata dovrebbe essere vuota");
	}
	
	@Test
	public void testIsEmptyBorsaNonVuota() {
		this.borsa=creaBorsaConUnAttrezzo("martello");
		assertFalse(this.borsa.isEmpty(),"La borsa non dovrebbe essere vuota dopo un addAttrezzo");
	}
	
	@Test
	public void testGetPesoBorsaVuota() {
		assertEquals(0,this.borsa.getPeso(),"Il peso di una borsa vuota deve essere 0");
	}
	
	@Test
	public void testGetPesoBorsaNonVuota() {
		this.borsa=creaBorsaConUnAttrezzo("martello");
		assertEquals(3,this.borsa.getPeso(),"Il peso della borsa dovrebbe corrispondere alla somma dei pesi degli attrezzi");
	}
	
	@Test
	public void testHasAttrezzoBorsaVuota() {
		assertFalse(this.borsa.hasAttrezzo("martello"),() -> "hasAttrezzo deve restituire false se la borsa è vuota");
	}
	
	@Test
	public void testHasAttrezzoPresenteInBorsa() {
		this.borsa=creaBorsaConUnAttrezzo("martello");
		assertTrue(this.borsa.hasAttrezzo("martello"),"La borsa dovrebbe confermare la presenza del martello");
	}
	
	@Test
	public void testHasAttrezzoNonPresente() {
		this.borsa=creaBorsaConUnAttrezzo("martello");
		assertFalse(this.borsa.hasAttrezzo("chiodo"));
	}
	
	@Test
	public void testRemoveAttrezzoBorsaVuota() {
		assertNull(this.borsa.removeAttrezzo("chiodo"),()->"Rimuovere da una borsa vuota deve restituire null");
	}
	
	@Test
	public void testRemoveAttrezzoPresenteInBorsa() {
		this.borsa=creaBorsaConUnAttrezzo("martello");
		assertEquals("martello",this.borsa.removeAttrezzo("martello").getNome(),"L'attrezzo rimosso dovrebbe essere il martello");
	}
	
	@Test
	public void testRemoveAttrezzoNonPresenteInBorsa() {
		this.borsa=creaBorsaConUnAttrezzo("martello");
		assertNull(this.borsa.removeAttrezzo("chiodo"),()->"Rimuovere da una borsa un attrezzo non presente dovrebbe restituire null");
	}
	
	@Test
	public void testToStringBorsaConAttrezzo() {
		this.borsa=creaBorsaConUnAttrezzo("martello");
		String desc=this.borsa.toString();
		assertTrue(desc.contains("martello"),"La stringa descrittiva dovrebbe contenere il nome dell'attrezzo in borsa");
	}
	
	@Test
	public void testToStringBorsaVuota() {
		String desc=this.borsa.toString();
		assertTrue(desc.contains("vuota"),"La stringa descrittiva di una borsa vuota dovrebbe contenere la parola 'vuota'");
	}
	
	@Test
	public void testAddAttrezzoBorsaGiaPiena() {
		this.borsa=creaBorsaConPiuAttrezzi("a","b","c","d","e","f","g","h","i","l");
		Attrezzo diTroppo=new Attrezzo("m",1);
		assertFalse(this.borsa.addAttrezzo(diTroppo),"Non dovrebbe essere possibile aggiungere l'undicesimo attrezzo (limite fisico)");
	}
	@Test
	public void testAddAttrezzoBorsaConPesoMassimo() {
		this.borsa=creaBorsaConPesoMax("martello");
		assertFalse(this.borsa.addAttrezzo(new Attrezzo("chiodo",1)),"Non dovrebbe essere possibile aggiungere attrezzi se si supera il peso massimo di 10kg");
	}
	

	
}
