package eu.andreatt.proyecto1_dein.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eu.andreatt.proyecto1_dein.bbdd.ConexionBD;
import eu.andreatt.proyecto1_dein.model.InformacionEvento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EventoDao {
	private ConexionBD conexion;

	/** CARGAR LOS EVENTOS EXISTENTES EN BASE DE DATOS */
	public ObservableList<InformacionEvento> cargarEventos() {

		ObservableList<InformacionEvento> evento = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBD();
			String consulta = "SELECT e.nombre AS nombre, o.nombre AS nombreOlimpiada, d.nombre AS nombreDeporte FROM evento e JOIN olimpiada o ON e.id_olimpiada = o.id_olimpiada JOIN deporte d ON e.id_deporte = d.id_deporte;";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String nombreOlimpiada = rs.getString("nombreOlimpiada");
				String nombreDeporte = rs.getString("nombreDeporte");

				evento.add(new InformacionEvento(nombre, nombreOlimpiada, nombreDeporte));
			}
			rs.close();
			conexion.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return evento;
	}
	
	/** CARGAR LOS NOMBRES DE EVENTO EN BASE DE DATOS */
	public ObservableList<String> cargarNombresDeEventos() {

		ObservableList<String> nombre = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBD();
			String consulta = "SELECT DISTINCT nombre as nombre FROM evento;";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				nombre.add(rs.getString("nombre"));
			}
			rs.close();
			conexion.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nombre;
	}
	
	/** INSERTAR EVENTO EN BASE DE DATOS */
	public boolean insertarEvento(String nombre, int id_olimpiada, int id_deporte) {
		try {
			conexion = new ConexionBD();       
			String consulta = "INSERT INTO evento (nombre, id_olimpiada, id_deporte) VALUES (?, ?, ?)";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setString(1, nombre);
	        pstmt.setInt(2, id_olimpiada);
	        pstmt.setInt(3, id_deporte);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** BORRAR EVENTO EN BASE DE DATOS */
	public boolean borrarEvento(int id_evento) {
		try {
			conexion = new ConexionBD();       
			String consulta = "DELETE FROM evento WHERE id_evento = ?";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setInt(1, id_evento);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** BORRAR EVENTO EN BASE DE DATOS */
	public boolean borrarEventoPorOlimpiada(int id_olimpiada) {
		try {
			conexion = new ConexionBD();       
			String consulta = "DELETE FROM evento WHERE id_olimpiada = ?";
	
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
	
	/** ACTUALIZAR EVENTO EN BASE DE DATOS */
	public boolean actualizarEvento(int id_evento, String nombre, int id_olimpiada, int id_deporte) {
		try {
			conexion = new ConexionBD();       
			String consulta = "UPDATE evento SET nombre = ?, id_olimpiada = ?, id_deporte = ? WHERE id_evento = ?";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setString(1, nombre);
	        pstmt.setInt(2, id_olimpiada);
	        pstmt.setInt(3, id_deporte);
	        pstmt.setInt(4, id_evento);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** OBTENER EL ID DEL EVENTO SOLICITADO */
	public int dameIdDeEvento(String nombre) {
	    try {
	        conexion = new ConexionBD();
	        String consulta = "SELECT id_evento FROM evento WHERE nombre = ?";
	        PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setString(1, nombre);

	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            return rs.getInt("id_evento");
	        }

	        rs.close();
	        conexion.closeConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1;
	}
	
	/** OBTENER EL ID DEL EVENTO SOLICITADO */
	public int dameIdDeEventoPorDeporte(int id_deporte) {
	    try {
	        conexion = new ConexionBD();
	        String consulta = "SELECT id_evento FROM evento WHERE id_deporte = ?";
	        PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setInt(1, id_deporte);

	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            return rs.getInt("id_evento");
	        }

	        rs.close();
	        conexion.closeConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1;
	}
	
	/** OBTENER EL ID DEL EVENTO SOLICITADO */
	public int dameIdDeEventoPorOlimpiada(int id_olimpiada) {
	    try {
	        conexion = new ConexionBD();
	        String consulta = "SELECT id_evento FROM evento WHERE id_olimpiada = ?";
	        PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setInt(1, id_olimpiada);

	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            return rs.getInt("id_evento");
	        }

	        rs.close();
	        conexion.closeConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1;
	}
	
	/** VERIFICAR SI EXISTE EL EVENTO EN BASE DE DATOS */
	public boolean existeEvento(String nombre, int id_olimpiada, int id_deporte) {
	    try {
	        conexion = new ConexionBD();
	        String consulta = "SELECT * FROM evento WHERE nombre = ? AND id_olimpiada = ? AND id_deporte = ?";
	        PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);

	        pstmt.setString(1, nombre);
	        pstmt.setInt(2, id_olimpiada);
	        pstmt.setInt(3, id_deporte);

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