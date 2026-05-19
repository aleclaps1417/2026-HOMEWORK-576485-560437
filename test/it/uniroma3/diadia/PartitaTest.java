package it.uniroma3.diadia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class PartitaTest {
    
    private Partita partita; 
    private Labirinto labirinto;

    @Before
    public void setUp() {
    	this.labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		this.partita = new Partita(this.labirinto);
    }
    
    // ==============================================
    // TEST VINTA
    // ==============================================
    
    @Test
    public void testVintaInizioPartitaFalse() {
        //Appena creata, la partita non è vinta
        assertFalse(this.partita.vinta());
    }
    
    @Test
    public void testVintaVintaTrue() {
        // Spostiamo il giocatore nella stanza vincente
        Stanza stanzaVincente = this.partita.getLabirinto().getStanzaVincente();
        this.partita.setStanzaCorrente(stanzaVincente);
        assertTrue(this.partita.vinta());
    }
    
    @Test
    public void testVintaStanzaQualunqueFalse() {
        // Spostiamo il giocatore in una stanza non vincente
        Stanza stanzaCorrente = new Stanza("Stanza corrente");
        this.partita.setStanzaCorrente(stanzaCorrente);
        assertFalse(this.partita.vinta());
    }
    
    // ==============================================
    // TEST ISFINITA
    // ==============================================
    
    @Test
    public void testIsFinitaInizioPartitaFalse() {
        // Appena creata, la partita non è finita
        assertFalse(this.partita.isFinita());
    }
    
    @Test
    public void testIsFinitaVintaTrue() {
        // Se la partita è vinta, risulta anche finita
        Stanza stanzaVincente = this.partita.getLabirinto().getStanzaVincente();
        this.partita.setStanzaCorrente(stanzaVincente);
        assertTrue(this.partita.isFinita());
    }
    
    @Test
    public void testIsFinitaCfuZeroTrue() {
        //Se i CFU scendono a 0, la partita è finita
       // this.partita.setCfu(0);
    	this.partita.getGiocatore().setCfu(0);
        assertTrue(this.partita.isFinita());
    }
    @Test
    public void testSetFinita() {
    	this.partita.setFinita();
    	assertTrue(this.partita.isFinita());
    }
    @Test
    public void testGiocatoreIsVivoNo() {
    	this.partita.getGiocatore().setCfu(0);
    	assertFalse("Il giocatore dovrebbe essere morto",this.partita.giocatoreIsVivo());
    }
    
    @Test
    public void testGiocatoreIsVivoSi() {
    	assertTrue("Ad inizio partita il giocatore ha i cfu quindi dovrebbe essere vivo"
    			,this.partita.giocatoreIsVivo());
    }
    
}