package eu.andreatt.proyecto1_dein.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eu.andreatt.proyecto1_dein.bbdd.ConexionBD;
import eu.andreatt.proyecto1_dein.model.InformacionParticipacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ParticipacionDao {
	private ConexionBD conexion;

	/** CARGAR LAS PARTICIPACIONES EXISTENTES EN BASE DE DATOS */
	public ObservableList<InformacionParticipacion> cargarParticipaciones() {

		ObservableList<InformacionParticipacion> participacion = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBD();
			String consulta = "SELECT d.nombre AS nombreDeportista, e.nombre AS nombreEvento, te.nombre AS nombreEquipo, p.edad, p.medalla FROM Participacion p JOIN Deportista d ON p.id_deportista = d.id_deportista JOIN Evento e ON p.id_evento = e.id_evento JOIN Equipo te ON p.id_equipo = te.id_equipo;";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String nombreDeportista = rs.getString("nombreDeportista");
				String nombreEvento = rs.getString("nombreEvento");
				String nombreEquipo = rs.getString("nombreEquipo");
				int edad = rs.getInt("edad");
				String medalla = rs.getString("medalla");

				participacion.add(new InformacionParticipacion(nombreDeportista, nombreEvento, nombreEquipo, edad, medalla));
			}
			rs.close();
			conexion.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return participacion;
	}
	
	/** INSERTAR PARTICIPACIÓN EN BASE DE DATOS */
	public boolean insertarParticipacion(int id_deportista, int id_evento, int id_equipo, int edad, String medalla) {
		try {
			conexion = new ConexionBD();       
			String consulta = "INSERT INTO Participacion (id_deportista, id_evento, id_equipo, edad, medalla) VALUES (?, ?, ?, ?, ?)";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setInt(1, id_deportista);
	        pstmt.setInt(2, id_evento);
	        pstmt.setInt(3, id_equipo);
	        pstmt.setInt(4, edad);
	        pstmt.setString(5, medalla);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** BORRAR PARTICIPACIÓN EN BASE DE DATOS */
	public boolean borrarParticipacion(int id_deportista, int id_evento, int id_equipo, int edad) {
		try {
			conexion = new ConexionBD();       
			String consulta = "DELETE FROM Participacion WHERE id_deportista = ? AND id_evento = ? AND id_equipo = ? AND edad = ?";
			
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setInt(1, id_deportista);
	        pstmt.setInt(2, id_evento);
	        pstmt.setInt(3, id_equipo);
	        pstmt.setInt(4, edad);

	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** BORRAR PARTICIPACIÓN EN BASE DE DATOS */
	public boolean borrarParticipacionPorEvento(int id_evento) {
		try {
			conexion = new ConexionBD();       
			String consulta = "DELETE FROM Participacion WHERE id_evento = ?";
	
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
	
	/** BORRAR PARTICIPACIÓN EN BASE DE DATOS */
	public boolean borrarParticipacionPorDeportista(int id_deportista) {
		try {
			conexion = new ConexionBD();       
			String consulta = "DELETE FROM Participacion WHERE id_deportista = ?";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setInt(1, id_deportista);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** BORRAR PARTICIPACIÓN EN BASE DE DATOS */
	public boolean borrarParticipacionPorEquipo(int id_equipo) {
		try {
			conexion = new ConexionBD();       
			String consulta = "DELETE FROM Participacion WHERE id_equipo = ?";
	
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
	
	/** ACTUALIZAR PARTICIPACIÓN EN BASE DE DATOS */
	public boolean actualizarParticipacion(int id_deportista, int id_evento, int id_equipo, int edad, String medalla) {
		try {
			conexion = new ConexionBD();       
			String consulta = "UPDATE Participacion SET nombre = ?, sexo = ?, peso = ?, altura = ? WHERE id_deportista = ?";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setInt(1, id_deportista);
	        pstmt.setInt(2, id_evento);
	        pstmt.setInt(3, id_equipo);
	        pstmt.setInt(4, edad);
	        pstmt.setString(5, medalla);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
		
	/** VERIFICAR SI EXISTE LA PARTICIPACIÓN EN BASE DE DATOS */
	public boolean existeParticipacion(int id_deportista, int id_evento, int id_equipo, int edad) {
	    try {
	        conexion = new ConexionBD();
	        String consulta = "SELECT * FROM Participacion WHERE id_deportista = ? AND id_evento = ? AND id_equipo = ? AND edad = ?";
	        PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);

	        pstmt.setInt(1, id_deportista);
	        pstmt.setInt(2, id_evento);
	        pstmt.setInt(3, id_equipo);
	        pstmt.setInt(4, edad);

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