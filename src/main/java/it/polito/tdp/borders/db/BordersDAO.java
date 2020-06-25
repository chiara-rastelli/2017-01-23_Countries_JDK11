package it.polito.tdp.borders.db;

import it.polito.tdp.borders.model.Adiacenza;
import it.polito.tdp.borders.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BordersDAO {

	public List<Adiacenza> loadAllAdiacenze(int year, Map<Integer, Country> countriesIdMap) {
		
		String sql ="	SELECT c1.CCode, c2.CCode FROM country c1, contiguity co, country c2 WHERE co.conttype = 1 " + 
					"	AND c1.StateAbb < c2.StateAbb AND c1.StateAbb = co.state1ab AND c2.StateAbb = co.state2ab " + 
					"	AND co.year < ?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, year);
			ResultSet rs = st.executeQuery();
			List<Adiacenza> list = new LinkedList<>();
			while( rs.next() ) {
				Country c1 = countriesIdMap.get(rs.getInt("c1.CCode"));
				Country c2 = countriesIdMap.get(rs.getInt("c2.CCode"));
				Adiacenza aTemp = new Adiacenza(c1, c2);
				list.add(aTemp);
			}
			conn.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	}
	
	public List<Country> loadAllCountries() {
		
		String sql = 
				"SELECT ccode,StateAbb,StateNme " +
				"FROM country " +
				"ORDER BY StateAbb " ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			List<Country> list = new LinkedList<Country>() ;
			
			while( rs.next() ) {
				
				Country c = new Country(
						rs.getInt("ccode"),
						rs.getString("StateAbb"), 
						rs.getString("StateNme")) ;
				
				list.add(c) ;
			}
			
			conn.close() ;
			
			return list ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	}
	
	public List<Integer> loadAllYears() {
		String sql = "SELECT DISTINCT year FROM contiguity ORDER BY YEAR asc";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			List<Integer> list = new LinkedList<>();
			while( rs.next() ) {
				list.add(rs.getInt("year"));
			}
			conn.close();
			return list ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}
}

