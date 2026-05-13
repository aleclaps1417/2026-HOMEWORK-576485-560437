package it.uniroma3.diadia.giocatore;

import java.util.Map;
import java.util.HashMap;

import it.uniroma3.diadia.attrezzi.Attrezzo;



public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA=10;
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	public Borsa(int pesoMax ) {
		this.pesoMax=pesoMax;
		this.attrezzi = new HashMap<>();
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.getPeso()+attrezzo.getPeso()>this.getPesoMax()) {
			return false;
		}
		
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);	
}

	public int getPeso() {
		int peso=0;
		for (Attrezzo a : this.attrezzi.values()) {
			peso += a.getPeso();
		}
		return peso;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa (").append(this.getPeso()).append("kg/").append(this.getPesoMax()).append("kg): ");
			for (Attrezzo a : this.attrezzi.values()) {
				s.append(a.toString()).append(" ");
			}
		} else {
			s.append("Borsa vuota");
		}
		return s.toString();
	}
}
