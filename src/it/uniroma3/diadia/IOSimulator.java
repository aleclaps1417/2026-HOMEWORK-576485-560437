package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IOSimulator implements IO {

    private List<String> righeDaLeggere;
    private int indiceRigheLette;
    
    private List<String> messaggiProdotti;
    private int indiceMessaggiMostrati;

    // La mappa richiesta dal professore per associare Turno -> Messaggi
    private Map<Integer, List<String>> messaggiPerRigaLetta;
    private int turnoCorrente;

    public IOSimulator(List<String> righeDaLeggere) {
        this.righeDaLeggere = righeDaLeggere;
        this.indiceRigheLette = 0;
        this.messaggiProdotti = new ArrayList<>();
        this.indiceMessaggiMostrati = 0;
        
        this.messaggiPerRigaLetta = new HashMap<>();
        this.turnoCorrente = -1; // -1 rappresenta i messaggi di benvenuto prima del primo comando
    }

    @Override
    public String leggiRiga() {
        String riga = null;
        if (this.indiceRigheLette < this.righeDaLeggere.size()) {
            this.turnoCorrente = this.indiceRigheLette; // Aggiorniamo il turno
            riga = this.righeDaLeggere.get(this.indiceRigheLette);
            this.indiceRigheLette++;
        }
        return riga;
    }

    @Override
    public void mostraMessaggio(String msg) {
        this.messaggiProdotti.add(msg);
        
        // Inseriamo il messaggio nella mappa associandolo al turno corrente
        if (!this.messaggiPerRigaLetta.containsKey(this.turnoCorrente)) {
            this.messaggiPerRigaLetta.put(this.turnoCorrente, new ArrayList<>());
        }
        this.messaggiPerRigaLetta.get(this.turnoCorrente).add(msg);
    }

    // Metodo utilissimo per i test: recupera le risposte a un comando specifico!
    public List<String> getMessaggiDellaRiga(int indiceRiga) {
        return this.messaggiPerRigaLetta.get(indiceRiga);
    }

    public String nextMessaggio() {
        String next = null;
        if (this.indiceMessaggiMostrati < this.messaggiProdotti.size()) {
            next = this.messaggiProdotti.get(this.indiceMessaggiMostrati);
            this.indiceMessaggiMostrati++;
        }
        return next;
    }

    public boolean hasNextMessaggio() {
        return this.indiceMessaggiMostrati < this.messaggiProdotti.size();
    }
}