package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccettazioneTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	public void testFine() {
		String[]comandi= {"fine"};
		IOSimulator simulatore=new IOSimulator(comandi);
		DiaDia gioco=new DiaDia(simulatore);
		
		gioco.gioca();
		
		simulatore.nextMessaggio();//Salta il benvenuto
		assertEquals("Grazie per aver giocato",simulatore.nextMessaggio());		
	}
	
	@Test
	public void testVaiDrittoInBiblioteca() {
		// Dall'Atrio andiamo a Nord (Biblioteca) e poi chiudiamo
		String[] comandi = {"vai nord", "fine"};
		IOSimulator simulatore = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(simulatore);
		
		gioco.gioca();
		
		
		simulatore.nextMessaggio(); // Salta il Benvenuto
		String msgSpostamento = simulatore.nextMessaggio(); 
		
		// Verifichiamo che la descrizione della stanza contenga 'Biblioteca'
		assertTrue("Dovrei essere in Biblioteca", msgSpostamento.contains("Biblioteca"));
	}
	@Test
	public void testAiuto() {
		String[] comandi = {"aiuto", "fine"};
		IOSimulator io = new IOSimulator(comandi);
		new DiaDia(io).gioca();
		
		io.nextMessaggio(); // Benvenuto
		String msgAiuto = io.nextMessaggio();
		
		assertTrue("Dovrebbero essere riprodotti alcuni messaggi di base(vai)",msgAiuto.contains("vai"));
		assertTrue("Dovrebbero essere riprodotti alcuni messaggi di base(vai)",msgAiuto.contains("aiuto"));
	}


}
