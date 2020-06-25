package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	BordersDAO db;
	Map<Integer, Country> countriesIdMap;
	SimpleGraph<Country, DefaultEdge> graph;
	
	public Model() {
		this.db = new BordersDAO();
		this.countriesIdMap = new HashMap<>();
		for (Country c : this.db.loadAllCountries())
			this.countriesIdMap.put(c.getcCode(), c);
	}
	
	public List<Integer> getAllYears(){
		return this.db.loadAllYears();
	}

	public void creaGrafo(int anno) {
		this.graph = new SimpleGraph<>(DefaultEdge.class);
		for (Adiacenza a : this.db.loadAllAdiacenze(anno, countriesIdMap)) {
			if (!this.graph.containsVertex(a.getC1()))
				this.graph.addVertex(a.getC1());
			if (!this.graph.containsVertex(a.getC2()))
				this.graph.addVertex(a.getC2());
			if (!this.graph.containsEdge(a.getC1(), a.getC2()))
				Graphs.addEdgeWithVertices(this.graph, a.getC1(), a.getC2());
		}
		System.out.println("Grafo creato con "+this.graph.vertexSet().size()+" vertici e "+this.graph.edgeSet().size()+" archi!\n");
	}
	
	public List<CountryWithNeighbors> getCountryAndConfinanti(){
		List<CountryWithNeighbors> list = new ArrayList<CountryWithNeighbors>();
		for (Country c : this.graph.vertexSet())
			list.add(new CountryWithNeighbors(c, this.graph.degreeOf(c)));
		Collections.sort(list);
		return list;
	}
	
}
