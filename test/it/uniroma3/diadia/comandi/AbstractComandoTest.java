package it.uniroma3.diadia.comandi;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder; 

public class AbstractComandoTest {

    private Comando cc; 
    private Partita p;
    
    @Before
    public void setUp() throws Exception {
        cc = new ConcreteComando();
        
        Labirinto labirintoDiTest = new LabirintoBuilder().addStanzaIniziale("Atrio").getLabirinto();
                
        p = new Partita(labirintoDiTest);
    }

    @After
    public void tearDown() throws Exception {
    	
    }

    @Test
    public void testConcreteComandoGetNome() {
         assertEquals("ConcreteComando", cc.getNome());
    }
    
    @Test
    public void testConcreteComandoParametro() {
        cc.setParametro("pippo");
        assertEquals("pippo", cc.getParametro());
    }

}