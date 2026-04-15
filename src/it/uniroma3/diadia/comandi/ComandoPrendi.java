package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando{
	private String nomeAttrezzo;
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
	}
	
	@Override
	public void esegui(Partita partita,IO io) {
		if(this.nomeAttrezzo==null) {
			io.mostraMessaggio("Quale attrezzo vuoi prendere ?");
			return ;
		}
		Stanza stanzaCorrente=partita.getStanzaCorrente();
		Attrezzo attrezzo=stanzaCorrente.getAttrezzo(this.nomeAttrezzo);
		if(attrezzo!=null) {
			boolean aggiunto=partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
			if(aggiunto) {
				stanzaCorrente.removeAttrezzo(attrezzo);
				io.mostraMessaggio("Hai preso: "+nomeAttrezzo);
			}
			else {
				io.mostraMessaggio("Borsa troppo piena");
			}
		}
		else {
			io.mostraMessaggio("Attrezzo non presente nella stanza ");
		}
	}
	

}
