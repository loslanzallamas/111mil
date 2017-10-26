package com.biblioteca.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.biblioteca.bd.JDBCMySQLConnection;
import com.biblioteca.model.Autor;

public class AutorRepository {
	
	Autor autor = null;
	
	public Autor getAutor(int autorId) {
		ResultSet rs = null;
		Connection conn = null;
		Statement st = null;
		
		Autor autor = null;
		String query = "SELECT * FROM autor WHERE id=" + autorId;
		
		return null;
	}

	public ArrayList<Autor> getAutores(){
		
		ResultSet rs = null;
		Connection conn = null;
		Statement st = null;
		
		ArrayList<Autor> autores = new  ArrayList<>();
		
		Autor autor = null;
		String query = "SELECT * FROM autor";
		
		try {
			conn = JDBCMySQLConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				autor = new Autor();
				autor.setNombre(rs.getString("Nombre"));
				autor.setNacionalidad(rs.getString("Nacionalidad"));
				autor.setFechaNac(LocalDate.parse((rs.getDate("Fecha")).toString()));
				autores.add(autor);
			}
		}catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch (Exception e) {
					System.out.println(e.getMessage());
				} 
			}
		}
		
		return autores;
	
	}
	
}
