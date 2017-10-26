package com.biblioteca.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import com.biblioteca.bd.JDBCMySQLConnection;
import com.biblioteca.model.Autor;

public class DaoAutorRepository{
	
	public Autor getById(int autorId) {
		ResultSet rs = null;
		Connection conn = null;
		Statement st = null;
		
		Autor autor = null;
		String query = "SELECT * FROM autor WHERE id=" + autorId;
		
		try {
			conn = JDBCMySQLConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				autor = new Autor();
				autor.setNombre(rs.getString("Nombre"));
				autor.setNacionalidad(rs.getString("Nacionalidad"));
				autor.setFechaNac(LocalDate.parse((rs.getDate("Fecha")).toString()));
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
		
		return autor;
	}

	public ArrayList<Autor> getAll(){
		
		ResultSet rs = null;
		Connection conn = null;
		Statement st = null;
		
		ArrayList<Autor> autores = new  ArrayList<>();
		
		Autor autor;
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
			ex.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
		
		return autores;
		
	}
	
	public void save(Autor autor) {
		
		ResultSet rs = null;
		Connection conn = null;
		Statement st = null;
		
		String query = "INSERT INTO autor('Nombre', 'Nacionalidad', 'Fecha') VALUES(" + autor.getNombre() + "," + autor.getNacionalidad() +
				"," + Date.from(autor.getFechaNac().atStartOfDay(ZoneId.systemDefault()).toInstant()) + ")";
		
		try {
			conn = JDBCMySQLConnection.getConnection();
			st = conn.createStatement();
			st.executeUpdate(query);
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
	}
	
	public void remove(Integer id) {
		
	}
}
