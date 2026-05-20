package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {
	
	// RIMOSSA: private String direzione; (non serve più!)
	
	@Override
	public void esegui(Partita partita, IO io) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		
	
		String direzione = this.getParametro();
		
		if(direzione == null) {
			io.mostraMessaggio("Dove vuoi andare ? Devi specificare una direzione ");
			return ;
		}
		
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		
		if(prossimaStanza == null) {
			io.mostraMessaggio("Direzione inesistente");
			return ;
		}
		
		partita.setStanzaCorrente(prossimaStanza);
		io.mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - 1);
	}
	
	@Override
	public String getNome() {
		return "vai";
	}

}
