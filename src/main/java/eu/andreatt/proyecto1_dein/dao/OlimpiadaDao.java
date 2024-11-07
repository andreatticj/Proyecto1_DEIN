package eu.andreatt.proyecto1_dein.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eu.andreatt.proyecto1_dein.bbdd.ConexionBD;
import eu.andreatt.proyecto1_dein.model.Olimpiada;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OlimpiadaDao {
	private ConexionBD conexion;
	
	/** CARGAR LAS OLIMPIADAS EXISTENTES EN BASE DE DATOS */
	public ObservableList<Olimpiada> cargarOlimpiadas() {

		ObservableList<Olimpiada> equipo = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBD();
			String consulta = "SELECT * FROM olimpiada";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int idOlimpiada = rs.getInt("id_olimpiada");
				String nombre = rs.getString("nombre");
				int anio = rs.getInt("anio");
				String temporada = rs.getString("temporada");
				String ciudad = rs.getString("ciudad");

				equipo.add(new Olimpiada(idOlimpiada, nombre, anio, temporada, ciudad));
			}
			rs.close();
			conexion.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equipo;
	}
	
	/** INSERTAR OLIMPIADA EN BASE DE DATOS */
	public boolean insertarOlimpiada(String nombre, int anio, String temporada, String ciudad) {
		try {
			conexion = new ConexionBD();       
			String consulta = "INSERT INTO olimpiada (nombre, anio, temporada, ciudad) VALUES (?, ?, ?, ?)";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setString(1, nombre);
	        pstmt.setInt(2, anio);
	        pstmt.setString(3, temporada);
	        pstmt.setString(4, ciudad);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** BORRAR OLIMPIADA EN BASE DE DATOS */
	public boolean borrarOlimpiada(int id_olimpiada) {
		try {
			conexion = new ConexionBD();       
			String consulta = "DELETE FROM olimpiada WHERE id_olimpiada = ?";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setInt(1, id_olimpiada);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** ACTUALIZAR OLIMPIADA EN BASE DE DATOS */
	public boolean actualizarOlimpiada(int id_olimpiada, String nombre, int anio, String temporada, String ciudad) {
		try {
			conexion = new ConexionBD();       
			String consulta = "UPDATE olimpiada SET nombre = ?, anio = ?, temporada = ?, ciudad = ? WHERE id_olimpiada = ?";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setString(1, nombre);
	        pstmt.setInt(2, anio);
	        pstmt.setString(3, temporada);
	        pstmt.setString(4, ciudad);
	        pstmt.setInt(5, id_olimpiada);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** OBTENER EL ID DEL OLIMPIADA SOLICITADO */
	public int dameIdDeOlimpiada(String nombre) {
	    try {
	        conexion = new ConexionBD();
	        String consulta = "SELECT id_olimpiada FROM olimpiada WHERE nombre = ?";
	        PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setString(1, nombre);

	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            return rs.getInt("id_olimpiada");
	        }

	        rs.close();
	        conexion.closeConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1;
	}
	
	/** VERIFICAR SI EXISTE EL OLIMPIADA EN BASE DE DATOS */
	public boolean existeOlimpiada(String nombre) {
	    try {
	        conexion = new ConexionBD();
	        String consulta = "SELECT * FROM olimpiada WHERE nombre = ?";
	        PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);

	        pstmt.setString(1, nombre);

	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            return true;
	        }

	        rs.close();
	        conexion.closeConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
}