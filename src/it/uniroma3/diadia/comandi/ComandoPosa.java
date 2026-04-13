package it.uniroma3.diadia.comandi;

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
	public void esegui(Partita partita) {
		if(this.nomeAttrezzo==null) {
			System.out.println("Quale attrezzo vuoi posare ?");
		}
		
		Attrezzo attrezzo=partita.getGiocatore().getBorsa().getAttrezzo(this.nomeAttrezzo);
		if(attrezzo!=null) {
			boolean posato=partita.getStanzaCorrente().addAttrezzo(attrezzo);
			if(posato) {
				partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
				System.out.println("Hai posato: "+nomeAttrezzo);
			}
			else {
				System.out.println("Stanza piena");
			}
		}
		else {
			System.out.println("Attrezzo non presente in borsa");
		}
	}

}
