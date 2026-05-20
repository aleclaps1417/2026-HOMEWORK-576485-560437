package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comandi.*;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * @author  docente di POO 
 * @version base
 */
public class DiaDia {

    public static final String MESSAGGIO_BENVENUTO = ""+
            "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
            "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
            "I locali sono popolati da strani personaggi, " +
            "alcuni amici, altri... chissa!\n"+
            "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
            "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
            "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
            "Per conoscere le istruzioni usa il comando 'aiuto'.";

    private IO console;
    private Partita partita; 
    private Labirinto labirinto;
    
    // Costruttore a un parametro: crea un labirinto base di default (salva i vecchi test!)
    public DiaDia(IO console) {
        this.console = console;
        this.labirinto = new LabirintoBuilder()
                .addStanzaIniziale("Atrio")
                .addStanzaVincente("Biblioteca")
                .addAdiacenza("Atrio", "Biblioteca", "nord")
                .getLabirinto();
        this.partita = new Partita(this.labirinto);
    }
    
    // Costruttore a due parametri: massima flessibilità, riceve il labirinto dall'esterno
    public DiaDia(Labirinto labirinto, IO console) {
        this.labirinto = labirinto;
        this.partita = new Partita(this.labirinto);
        this.console = console;
    }

    public void gioca() {   
        this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);

        String istruzione; 
        do {     
            istruzione = this.console.leggiRiga();
        } while (!processaIstruzione(istruzione));
    }   

    /**
     * Processa una istruzione 
     *
     * @return true se l'istruzione e' terminata (fine gioco), false altrimenti
     */
    private boolean processaIstruzione(String istruzione) {
        Comando comandoDaEseguire;
        FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva(this.console);
        
        try {
            // Proviamo a costruire il comando con la reflection
            comandoDaEseguire = factory.costruisciComando(istruzione);
            
        } catch (Exception e) {
            // Catturiamo qualsiasi errore (ClassNotFound, NullPointer) 
            // e creiamo direttamente un comando vuoto di emergenza
            comandoDaEseguire = new ComandoNonValido();
        }
        
        // ERRORE RISOLTO: Passiamo sia la partita che la console!
        comandoDaEseguire.esegui(this.partita, this.console);
        
        if (this.partita.vinta()) {
            this.console.mostraMessaggio("Hai vinto!");
        }
        
        if (!this.partita.giocatoreIsVivo()) {
            this.console.mostraMessaggio("Hai esaurito i CFU...");
        }
        
        return this.partita.isFinita();
    }
    
    public static void main(String[] argc) {
        IO io = new IOConsole();

        // Configurazione dinamica del labirinto ufficiale nel main tramite il Builder
        Labirinto labirintoUfficiale = new LabirintoBuilder()
                .addStanzaIniziale("Atrio")
                .addAttrezzo("martello", 3)
                .addStanzaVincente("Biblioteca")
                .addAdiacenza("Atrio", "Biblioteca", "nord")
                .getLabirinto();

        DiaDia gioco = new DiaDia(labirintoUfficiale, io);
        gioco.gioca();
    }
}