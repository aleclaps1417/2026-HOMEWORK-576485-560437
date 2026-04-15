package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando{
	private String nomeAttrezzo;
	
	@Override
	public void setParametro(String paramentro) {
		this.nomeAttrezzo=paramentro;
	}
	@Override
	public void esegui(Partita partita,IO io) {
		if(this.nomeAttrezzo==null) {
			System.out.println("Quale attrezzo vuoi posare ?");
		}
		
		Attrezzo attrezzo=partita.getGiocatore().getBorsa().getAttrezzo(this.nomeAttrezzo);
		if(attrezzo!=null) {
			boolean posato=partita.getStanzaCorrente().addAttrezzo(attrezzo);
			if(posato) {
				partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
				io.mostraMessaggio("Hai posato: "+nomeAttrezzo);
			}
			else {
				io.mostraMessaggio("Stanza piena");
			}
		}
		else {
			io.mostraMessaggio("Attrezzo non presente in borsa");
		}
	}
	
	@Override
	public String getNome() {
		return "posa";
	}
	
	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
