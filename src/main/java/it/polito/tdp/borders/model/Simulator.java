package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Simulator {

	PriorityQueue<Event> queue;
	Model m;
	static int N = 1000;// numero immigrati
	Map<Country, Integer> mappaImmigrati;
	SimpleGraph<Country, DefaultEdge> graph;
	int tempoSimulazione;
	
	public Simulator(Model m, Country c) {
		this.m = m;
		this.queue = new PriorityQueue<>();
		this.mappaImmigrati = new HashMap<>();
		for (Country cTemp : this.m.getCountriesGrafo())
			this.mappaImmigrati.put(cTemp, 0);
		this.queue.add(new Event(1, 1000, c));
		this.graph = m.graph;
		this.tempoSimulazione = 0;
		this.run();
	}
	
		public void run() {
			while (!this.queue.isEmpty()) {
				Event e = this.queue.poll();
				processEvent(e);
			}
		}

		private void processEvent(Event e) {
			// calcolo stanziali e non
			int daSpostare = (int)(e.numeroImmigrati/2);
			int stanziali = e.numeroImmigrati - daSpostare;
			int inizialmentePresenti = this.mappaImmigrati.get(e.getDestinazione());
			this.mappaImmigrati.put(e.getDestinazione(), stanziali+inizialmentePresenti);
			
			this.tempoSimulazione = e.getTempo();
			
			int numeroConfinanti = this.graph.degreeOf(e.getDestinazione());
			if (numeroConfinanti > daSpostare) {
				this.mappaImmigrati.put(e.getDestinazione(), daSpostare+inizialmentePresenti+stanziali);
			}else {
				int nuovi = (int)(daSpostare/numeroConfinanti);
				int rimasti = daSpostare - nuovi*numeroConfinanti;
				for (Country c : Graphs.neighborListOf(this.graph, e.getDestinazione()))
					this.queue.add(new Event(e.getTempo()+1, nuovi, c));
				int stanzialiPresenti = this.mappaImmigrati.get(e.getDestinazione());
				this.mappaImmigrati.put(e.getDestinazione(), stanzialiPresenti+rimasti);
			}
		}
		
		public int getTempoSimulazione() {
			return this.tempoSimulazione;
		}
	
		public Map<Country, Integer> getMappa(){
			return this.mappaImmigrati;
		}
}
