package it.polito.tdp.borders.model;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Model m = new Model();
		m.creaGrafo(1900);
		for (CountryWithNeighbors c : m.getCountryAndConfinanti())
			System.out.print(c.toString());
	}

}
