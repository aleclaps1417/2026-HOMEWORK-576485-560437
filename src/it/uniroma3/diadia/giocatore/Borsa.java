package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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
		if (this.isEmpty()) {
			s.append("Borsa vuota");
			}
		 else {
			s.append("Contenuto borsa (").append(this.getPeso()).append("kg/").append(this.getPesoMax()).append("kg): ");
			//Ordinata per nome
			s.append("Per Nome: ").append(this.getContenutoOrdinatoPerNome().toString()).append("\n");
			//Ordinata per Peso (List)
	        s.append("Per Peso: ").append(this.getContenutoOrdinatoPerPeso().toString()).append("\n");
	        //Raggruppata per Peso (Map)
	        s.append("Raggruppato per peso: ").append(this.getContenutoRaggruppatoPerPeso().toString());
		 }
		return s.toString();
	}
	
	public SortedSet<Attrezzo>getContenutoOrdinatoPerNome(){
		return new TreeSet<>(this.attrezzi.values());
	}
	
	public List<Attrezzo>getContenutoOrdinatoPerPeso(){
		List<Attrezzo> ordinata=new ArrayList<>(this.attrezzi.values());
		Collections.sort(ordinata,new Comparator<Attrezzo>() {
			@Override
			public int compare(Attrezzo a1,Attrezzo a2) {
				int diffPeso=a1.getPeso()-a2.getPeso();
				if(diffPeso!=0) {
					return diffPeso;
				}
				return a1.getNome().compareTo(a2.getNome());
			}
		});
		return ordinata;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer,Set<Attrezzo>>finale=new HashMap<>();
		
		for(Attrezzo a :this.attrezzi.values()) {
			Set<Attrezzo> set=finale.get(a.getPeso());
			
			if(set==null) {
				set=new HashSet<>();
				finale.put(a.getPeso(), set);
			}
			set.add(a);
		}
		return finale;
	}
	
	public SortedSet<Attrezzo>getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo>ordinato=new TreeSet<>(new Comparator<Attrezzo>() {
				@Override
				public int compare(Attrezzo a1,Attrezzo a2) {
					int diffPeso=a1.getPeso()-a2.getPeso();
					if(diffPeso!=0) {
						return diffPeso;
					}
					return a1.getNome().compareTo(a2.getNome());
				}
		});
		ordinato.addAll(this.attrezzi.values());
		return ordinato;
	}
}
