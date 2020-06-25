package it.polito.tdp.borders.model;

public class Event implements Comparable<Event>{

	Integer tempo;
	int numeroImmigrati;
	Country destinazione;

	public Event(Integer tempo, int numeroImmigrati, Country destinazione) {
		super();
		this.tempo = tempo;
		this.numeroImmigrati = numeroImmigrati;
		this.destinazione = destinazione;
	}

	public Integer getTempo() {
		return tempo;
	}

	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}

	public int getNumeroImmigrati() {
		return numeroImmigrati;
	}

	public void setNumeroImmigrati(int numeroImmigrati) {
		this.numeroImmigrati = numeroImmigrati;
	}

	public Country getDestinazione() {
		return destinazione;
	}

	public void setDestinazione(Country destinazione) {
		this.destinazione = destinazione;
	}

	@Override
	public int compareTo(Event o) {
		return this.tempo.compareTo(o.tempo);
	}
}
