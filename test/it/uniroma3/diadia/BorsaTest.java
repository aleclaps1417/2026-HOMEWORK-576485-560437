package it.uniroma3.diadia;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
		assertEquals("martello",this.borsa.getAttrezzo("martello").getNome());
	}
	@Test
	public void testGetAttrezzoBorsaVuota() {
		assertNull(this.borsa.getAttrezzo("martello"));
	}
	@Test
	public void testGetAttrezzoNonPresente() {
		this.borsa=creaBorsaConUnAttrezzo("martello");
		assertNull(this.borsa.getAttrezzo("chiodo"));
	
	}
	
	
	@Test
	public void testIsEmptyBorsaVuota() {
		assertTrue(this.borsa.isEmpty());
	}
	
	@Test
	public void testIsEmptyBorsaNonVuota() {
		this.borsa=creaBorsaConUnAttrezzo("martello");
		assertFalse(this.borsa.isEmpty());
	}
	
	@Test
	public void testGetPesoBorsaVuota() {
		assertEquals(0,this.borsa.getPeso());
	}
	
	@Test
	public void testGetPesoBorsaNonVuota() {
		this.borsa=creaBorsaConUnAttrezzo("martello");
		assertEquals(3,this.borsa.getPeso());
	}
	
	@Test
	public void testHasAttrezzoBorsaVuota() {
		assertFalse(this.borsa.hasAttrezzo("martello"));
	}
	
	@Test
	public void testHasAttrezzoPresenteInBorsa() {
		this.borsa=creaBorsaConUnAttrezzo("martello");
		assertTrue(this.borsa.hasAttrezzo("martello"));
	}
	
	@Test
	public void testHasAttrezzoNonPresente() {
		this.borsa=creaBorsaConUnAttrezzo("martello");
		assertFalse(this.borsa.hasAttrezzo("chiodo"));
	}
	
	@Test
	public void testRemoveAttrezzoBorsaVuota() {
		assertNull(this.borsa.removeAttrezzo("chiodo"));
	}
	
	@Test
	public void testRemoveAttrezzoPresenteInBorsa() {
		this.borsa=creaBorsaConUnAttrezzo("martello");
		assertEquals("martello",this.borsa.removeAttrezzo("martello").getNome());
	}
	
	@Test
	public void testRemoveAttrezzoNonPresenteInBorsa() {
		this.borsa=creaBorsaConUnAttrezzo("martello");
		assertNull(this.borsa.removeAttrezzo("chiodo"));
	}
	
	@Test
	public void testToStringBorsaConAttrezzo() {
		this.borsa=creaBorsaConUnAttrezzo("martello");
		String desc=this.borsa.toString();
		assertTrue(desc.contains("martello"));
	}
	
	@Test
	public void testToStringBorsaVuota() {
		String desc=this.borsa.toString();
		assertTrue(desc.contains("vuota"));
	}
	
	@Test
	public void testAddAttrezzoBorsaGiaPiena() {
		this.borsa=creaBorsaConPiuAttrezzi("a","b","c","d","e","f","g","h","i","l");
		Attrezzo diTroppo=new Attrezzo("m",1);
		assertFalse(this.borsa.addAttrezzo(diTroppo));
	}
	@Test
	public void testAddAttrezzoBorsaConPesoMassimo() {
		this.borsa=creaBorsaConPesoMax("martello");
		assertFalse(this.borsa.addAttrezzo(new Attrezzo("chiodo",1)));
	}
	

	
}
