package it.uniroma3.diadia.comandi;

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
	public void esegui(Partita partita) {
		if(this.nomeAttrezzo==null) {
			System.out.println("Quale attrezzo vuoi prendere ?");
			return ;
		}
		Stanza stanzaCorrente=partita.getStanzaCorrente();
		Attrezzo attrezzo=stanzaCorrente.getAttrezzo(this.nomeAttrezzo);
		if(attrezzo!=null) {
			boolean aggiunto=partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
			if(aggiunto) {
				stanzaCorrente.removeAttrezzo(attrezzo);
				System.out.println("Hai preso: "+nomeAttrezzo);
			}
			else {
				System.out.println("Borsa troppo piena");
			}
		}
		else {
			System.out.println("Attrezzo non presente nella stanza ");
		}
	}
	

}
