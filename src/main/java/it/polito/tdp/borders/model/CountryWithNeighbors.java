package it.polito.tdp.borders.model;

public class CountryWithNeighbors implements Comparable<CountryWithNeighbors>{
	
	Country c;
	int numeroConfinanti;
	
	public CountryWithNeighbors(Country c, int numeroConfinanti) {
		super();
		this.c = c;
		this.numeroConfinanti = numeroConfinanti;
	}

	public Country getC() {
		return c;
	}

	public void setC(Country c) {
		this.c = c;
	}

	public int getNumeroConfinanti() {
		return numeroConfinanti;
	}

	public void setNumeroConfinanti(int numeroConfinanti) {
		this.numeroConfinanti = numeroConfinanti;
	}

	@Override
	public int compareTo(CountryWithNeighbors o) {
		return -Integer.compare(this.numeroConfinanti, o.numeroConfinanti);
	}

	@Override
	public String toString() {
		return "Country " + c.getStateName() + " --> numeroConfinanti: " + numeroConfinanti+"\n";
	}
	
}
