package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando{
	
	@Override
	public void esegui(Partita partita,IO io) {
		io.mostraMessaggio("Ti trovi in: "+ partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio("CFU residui: "+ partita.getGiocatore().getCfu());
		io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
	
	@Override
	public void setParametro(String parametro) {}
	

}
