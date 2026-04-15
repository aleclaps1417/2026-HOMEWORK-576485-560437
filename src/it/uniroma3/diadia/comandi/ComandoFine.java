package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	
	
	@Override
	public void setParametro(String parametro) {}
	@Override
	public void esegui(Partita partita,IO io) {
		io.mostraMessaggio("Grazie per aver giocato");
		partita.setFinita();
	}
}
