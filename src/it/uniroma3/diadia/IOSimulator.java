package it.uniroma3.diadia;

public class IOSimulator implements IO{
	private String[] righeDaLeggere;    // Array delle istruzioni "iniettate"
	private int indiceRigheLette;       
	
	private String[] messaggiStampati;  
	private int indiceMessaggiStampati; 
	private int indiceMessaggiMostrati; 

	public IOSimulator(String[] righeDaLeggere) {
		this.righeDaLeggere = righeDaLeggere;
		this.indiceRigheLette = 0;
		
		//Array di output con una dimensione generosa
		this.messaggiStampati = new String[1000]; 
		this.indiceMessaggiStampati = 0;
		this.indiceMessaggiMostrati = 0;
	}

	@Override
	public String leggiRiga() {
		String riga = this.righeDaLeggere[this.indiceRigheLette];
		this.indiceRigheLette++;
		return riga;
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		this.messaggiStampati[this.indiceMessaggiStampati] = messaggio;
		this.indiceMessaggiStampati++;
	}
	
	
	public String nextMessaggio() {
		String next = this.messaggiStampati[this.indiceMessaggiMostrati];
		this.indiceMessaggiMostrati++;
		return next;
	}

}
