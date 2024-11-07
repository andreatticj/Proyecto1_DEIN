package eu.andreatt.proyecto1_dein.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eu.andreatt.proyecto1_dein.bbdd.ConexionBD;
import eu.andreatt.proyecto1_dein.model.Equipo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EquipoDao {
	private ConexionBD conexion;

	/** CARGAR LOS EQUIPOS EXISTENTES EN BASE DE DATOS */
	public ObservableList<Equipo> cargarEquipos() {

		ObservableList<Equipo> equipo = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBD();
			String consulta = "SELECT * FROM Equipo";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int idEquipo = rs.getInt("id_equipo");
				String nombre = rs.getString("nombre");
				String iniciales = rs.getString("iniciales");

				equipo.add(new Equipo(idEquipo, nombre, iniciales));
			}
			rs.close();
			conexion.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equipo;
	}
	
	/** INSERTAR EQUIPO EN BASE DE DATOS */
	public boolean insertarEquipo(String nombre, String iniciales) {
		try {
			conexion = new ConexionBD();       
			String consulta = "INSERT INTO Equipo (nombre, iniciales) VALUES (?, ?)";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setString(1, nombre);
	        pstmt.setString(2, iniciales);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** BORRAR EQUIPO EN BASE DE DATOS */
	public boolean borrarEquipo(int id_equipo) {
		try {
			conexion = new ConexionBD();       
			String consulta = "DELETE FROM Equipo WHERE id_equipo = ?";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setInt(1, id_equipo);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** ACTUALIZAR EQUIPO EN BASE DE DATOS */
	public boolean actualizarEquipo(int id_equipo, String nombre, String iniciales) {
		try {
			conexion = new ConexionBD();       
			String consulta = "UPDATE Equipo SET nombre = ?, iniciales = ? WHERE id_equipo = ?";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setString(1, nombre);
	        pstmt.setString(2, iniciales);
	        pstmt.setInt(3, id_equipo);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** OBTENER EL ID DEL EQUIPO SOLICITADO */
	public int dameIdDeEquipo(String nombre) {
	    try {
	        conexion = new ConexionBD();
	        String consulta = "SELECT id_equipo FROM Equipo WHERE nombre = ?";
	        PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setString(1, nombre);

	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            return rs.getInt("id_equipo");
	        }

	        rs.close();
	        conexion.closeConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1;
	}
	
	/** VERIFICAR SI EXISTE EL EQUIPO EN BASE DE DATOS */
	public boolean existeEquipo(String nombre) {
	    try {
	        conexion = new ConexionBD();
	        String consulta = "SELECT * FROM Equipo WHERE nombre = ?";
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