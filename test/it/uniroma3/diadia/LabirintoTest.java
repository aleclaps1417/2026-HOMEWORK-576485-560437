package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {

    private Labirinto labirinto;
    
    @BeforeEach
    void setUp() {
        this.labirinto = new Labirinto();
    }
    
    // ---- TEST PER LA STANZA INIZIALE  ----
    
    @Test
    void testGetStanzaIniziale_Esistente() {
        assertNotNull(this.labirinto.getStanzaIniziale(), "La stanza iniziale deve essere istanziata");
    }

    @Test
    void testGetStanzaIniziale_CorrettoInsert() {
        assertEquals("Atrio", this.labirinto.getStanzaIniziale().getNome(), "Il nome della stanza iniziale deve essere Atrio");
    }
    
    @Test
    void testGetStanzaIniziale_HaStanzeAdiacenti() {
        Stanza adiacente = this.labirinto.getStanzaIniziale().getStanzaAdiacente("nord");
        assertNotNull(adiacente, "L'atrio dovrebbe avere una stanza adiacente a nord");
    }
    
    // ---- TEST PER LA STANZA VINCENTE  ----

    @Test
    void testGetStanzaVincente_Esistente() {
        assertNotNull(this.labirinto.getStanzaVincente(), "La stanza finale deve essere istanziata");
    }
    
    @Test
    void testGetStanzaVincente_CorrettoInsert() {
        assertEquals("Biblioteca", this.labirinto.getStanzaVincente().getNome(), "Il nome della stanza finale deve essere Biblioteca");
    }
    
    @Test
    void testGetStanzaVincente_DiversaDaIngresso() {
        assertNotEquals(this.labirinto.getStanzaIniziale(), this.labirinto.getStanzaVincente(), "Entrata e uscita non devono coincidere");
    }
}