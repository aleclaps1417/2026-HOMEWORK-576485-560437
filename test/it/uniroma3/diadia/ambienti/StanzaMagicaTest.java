package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
private StanzaMagica atrio;
	
	private void riempiStanzaMagica(Attrezzo...atrezzo) {
		for (Attrezzo attrezzo : atrezzo) {
			this.atrio.addAttrezzo(attrezzo);
		}
	}

	@BeforeEach
	void setUp() throws Exception {
		this.atrio=new StanzaMagica("atrio");
	}

	@Test
	public void testAddAttrezzoPrimaCheLaStanzaDiventiMagica() {
		this.riempiStanzaMagica(new Attrezzo("martello",2));
		assertEquals("Se aggiungo un martello mi aspetto che la stanza restituisca un martello",
				"martello",this.atrio.getAttrezzo("martello").getNome());	
	}
	
	@Test
	public void testAddAttrezzoConStanzaMagica() {
		this.riempiStanzaMagica(new Attrezzo("martello",2),new Attrezzo("chiave",2),new Attrezzo("chiodo",2) 
				,new Attrezzo("gomma",2));
		assertFalse("Dopo il terzo elemento aggiunto la stanza dovrebbe diventare magica e gomma non dovrebbe esserci(perchè modificata)",
				this.atrio.hasAttrezzo("gomma"));
		assertTrue("I primi tre attrezzi dovrebbero esserci",this.atrio.hasAttrezzo("martello"));
	}
	
	@Test
	public void modificaAttrezzoDelPeso() {
		this.riempiStanzaMagica(new Attrezzo("martello",2),new Attrezzo("chiave",2),new Attrezzo("chiodo",2) 
				,new Attrezzo("gomma",2));
		assertEquals("Il peso della gomma dovrebbe diventare 4 dopo la modifica",
				4,this.atrio.getAttrezzo("ammog").getPeso());
	}
	@Test
	public void modificaAttrezzoDelNome() {
		this.riempiStanzaMagica(new Attrezzo("martello",2),new Attrezzo("chiave",2),new Attrezzo("chiodo",2) 
				,new Attrezzo("gomma",2));
		assertEquals("Il nome della gomma dovrebbe diventare ammog dopo la modifica",
				"ammog",this.atrio.getAttrezzo("ammog").getNome());
	}
	

}
