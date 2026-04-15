package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa","guarda"};
	
	@Override
	public void esegui(Partita partita,IO io) {
		StringBuilder stringaComandi=new StringBuilder();
		for(String s: elencoComandi) {
			stringaComandi.append(s).append(" ");
		}
		
		io.mostraMessaggio(stringaComandi.toString());
	}
	
	@Override
	public void setParametro(String parametro) {};
	
	
}
