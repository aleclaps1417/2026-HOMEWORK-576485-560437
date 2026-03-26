package it.uniroma3.diadia;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	//NON PIU SUA COMPETENZA static final private int CFU_INIZIALI = 20;

	private Stanza stanzaCorrente;
	private Labirinto labirinto;// Nuovo riferimento richiesto
	private boolean finita;
	private Giocatore giocatore;
	//NON PIU SUA COMPETENZA private int cfu;
	
	public Partita(){
		this.labirinto = new Labirinto(); // Deleghiamo la creazione
		this.stanzaCorrente = this.labirinto.getStanzaIniziale(); // Prendiamo l'entrata dal labirinto
		this.finita = false;
		//NON PIU SUA COMPETENZA this.cfu = CFU_INIZIALI;
		this.giocatore=new Giocatore();
	}

	public Labirinto getLabirinto() {
        return this.labirinto;
    }
 
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		// Ora chiediamo al labirinto qual è la stanza vincente
		return this.getStanzaCorrente() == this.labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		//return finita || vinta() || (cfu == 0);
		return finita || vinta() || (this.giocatore.getCfu()==0) ;
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	/*
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}	
	*/
}
