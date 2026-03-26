package it.uniroma3.diadia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PartitaTest {
    
    private Partita partita; 

    @Before
    public void setUp() {
        this.partita = new Partita();
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
        Stanza stanzaVincente = this.partita.getStanzaVincente();
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
        Stanza stanzaVincente = this.partita.getStanzaVincente();
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
}