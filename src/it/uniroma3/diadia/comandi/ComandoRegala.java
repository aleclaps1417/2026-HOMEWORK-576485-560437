package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando {

	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita, IO io) {
	
		if (this.nomeAttrezzo == null) {
			io.mostraMessaggio("Cosa vorresti regalare? Specifica un attrezzo.");
			return;
		}

		
		if (partita.getGiocatore().getBorsa().hasAttrezzo(this.nomeAttrezzo)) {
			
			Attrezzo daRegalare = partita.getGiocatore().getBorsa().getAttrezzo(this.nomeAttrezzo);
			partita.getGiocatore().getBorsa().removeAttrezzo(this.nomeAttrezzo);
			
			io.mostraMessaggio("Hai deciso di regalare: " + daRegalare.getNome() + "! Che gesto nobile.");
		} else {
			io.mostraMessaggio("Non hai questo attrezzo nella borsa!");
		}
	}

	

	@Override
	public String getNome() {
		return "regala";
	}

	
}