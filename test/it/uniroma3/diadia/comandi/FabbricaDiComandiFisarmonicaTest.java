package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiFisarmonica fabbrica;

	@BeforeEach
	void setUp() throws Exception {
		this.fabbrica=new FabbricaDiComandiFisarmonica();
	}

	@Test
	void testCostruisciComandoVai() {
		Comando comando=this.fabbrica.costruisciComando("vai nord");
		assertEquals("vai",comando.getNome());
		assertEquals("nord",comando.getParametro());
	}
	
	@Test
	public void testCostruisciComandoPosa() {
		Comando comando=this.fabbrica.costruisciComando("posa martello");
		assertEquals("posa", comando.getNome());
		assertEquals("martello",comando.getParametro());
	}
	@Test
	public void testCostruisciComandoPrendi() {
		Comando comando=this.fabbrica.costruisciComando("prendi martello");
		assertEquals("prendi", comando.getNome());
		assertEquals("martello",comando.getParametro());
	}
	
	@Test
	public void testCostruisciComandoAiuto() {
		Comando comando=this.fabbrica.costruisciComando("aiuto");
		assertEquals("aiuto", comando.getNome());
	}
	@Test
	public void testCostruisciComandoFine() {
		Comando comando=this.fabbrica.costruisciComando("fine");
		assertEquals("fine", comando.getNome());
	}
	@Test
	public void testCostruisciComandoGuarda() {
		Comando comando=this.fabbrica.costruisciComando("guarda");
		assertEquals("guarda", comando.getNome());
	}
	@Test
	public void testCostruisciComandoComandoNonValido() {
		Comando comando=this.fabbrica.costruisciComando("Non valido");
		assertEquals("Non valido", comando.getNome());
	}
	@Test
	public void testCostruisciComandoNonValido() {
		
		Comando comando = this.fabbrica.costruisciComando("pulisci");
		
		assertEquals("Non valido",comando.getNome());
	}
	@Test
	public void testCostruisciComandoNull() {
		
		Comando comando = this.fabbrica.costruisciComando("");
		assertNotNull("La fabbrica deve comunque restituire un oggetto (non valido)", comando);
	}

}
