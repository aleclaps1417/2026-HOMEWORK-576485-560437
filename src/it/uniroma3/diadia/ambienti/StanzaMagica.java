package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza {
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	private static final int SOGLIA_MAGICA_DEFAULT = 3;

	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if (this.contatoreAttrezziPosati > this.sogliaMagica)
			attrezzo = this.modificaAttrezzo(attrezzo);
		return super.addAttrezzo(attrezzo); // Usa il metodo pubblico del padre
	}

	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito.reverse();
		int pesoRaddoppiato = attrezzo.getPeso() * 2;
		return new Attrezzo(nomeInvertito.toString(), pesoRaddoppiato);
	}
}