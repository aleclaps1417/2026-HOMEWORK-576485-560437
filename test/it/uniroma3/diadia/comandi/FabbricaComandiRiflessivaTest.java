package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOSimulator;

public class FabbricaComandiRiflessivaTest {

	private FabbricaDiComandiRiflessiva fabbrica;
	private IO io;

	@BeforeEach
	public void setUp() {
		// Creiamo un finto IO per soddisfare il costruttore della tua fabbrica
		this.io = new IOSimulator(new ArrayList<>());
		this.fabbrica = new FabbricaDiComandiRiflessiva(this.io);
	}

	@Test
	public void testComandoValidoSenzaParametro() throws Exception {
		Comando c = this.fabbrica.costruisciComando("fine");
		
		// Verifichiamo che abbia costruito il comando giusto
		assertEquals("fine", c.getNome());
		assertNull(c.getParametro());
	}

	@Test
	public void testComandoValidoConParametro() throws Exception {
		Comando c = this.fabbrica.costruisciComando("vai nord");
		
		// Verifichiamo che il nome sia "vai" e il parametro sia "nord"
		assertEquals("vai", c.getNome());
		assertEquals("nord", c.getParametro());
	}

	@Test
	public void testComandoInesistente_LanciaEccezione() {
		// CASO LIMITE: testiamo la lezione sulle eccezioni.
		// Diciamo a JUnit: "Mi aspetto che l'esecuzione di questo codice generi una Exception"
		assertThrows(Exception.class, () -> {
			
			this.fabbrica.costruisciComando("salta");
			
		}, "Un comando inesistente dovrebbe lanciare un'eccezione, ma non lo ha fatto!");
	}
}