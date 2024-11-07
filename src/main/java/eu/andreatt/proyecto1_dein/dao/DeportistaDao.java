package eu.andreatt.proyecto1_dein.dao;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eu.andreatt.proyecto1_dein.bbdd.ConexionBD;
import eu.andreatt.proyecto1_dein.model.Deportista;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DeportistaDao {
	private ConexionBD conexion;
	
	/** CARGAR LOS DEPORTISTAS EXISTENTES EN BASE DE DATOS */
	public ObservableList<Deportista> cargarDeportistas() {

		ObservableList<Deportista> deportista = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBD();
			String consulta = "SELECT id_deportista, nombre, sexo, peso, altura FROM Deportista";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int idDeportista = rs.getInt("id_deportista");
				String nombre = rs.getString("nombre");
				String sexo = rs.getString("sexo");
				int peso = rs.getInt("peso");
				int altura = rs.getInt("altura");

				deportista.add(new Deportista(idDeportista, nombre, sexo, peso, altura));
			}
			rs.close();
			conexion.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deportista;
	}
	
	/** CARGAR LOS DEPORTISTAS EXISTENTES EN BASE DE DATOS */
	public ObservableList<Deportista> cargarDeportistasFoto() {

		ObservableList<Deportista> deportista = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBD();
			String consulta = "SELECT * FROM Deportista";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int idDeportista = rs.getInt("id_deportista");
				String nombre = rs.getString("nombre");
				String sexo = rs.getString("sexo");
				int peso = rs.getInt("peso");
				int altura = rs.getInt("altura");
				InputStream foto = rs.getBinaryStream("foto");

				deportista.add(new Deportista(idDeportista, nombre, sexo, peso, altura, foto));
			}
			rs.close();
			conexion.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deportista;
	}
	
	/** INSERTAR DEPORTISTA EN BASE DE DATOS */
	public boolean insertarDeportista(String nombre, String sexo, int peso, int altura) {
		try {
			conexion = new ConexionBD();       
			String consulta = "INSERT INTO Deportista (nombre, sexo, peso, altura) VALUES (?, ?, ?, ?)";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setString(1, nombre);
	        pstmt.setString(2, sexo);
	        pstmt.setInt(3, peso);
	        pstmt.setInt(4, altura);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** BORRAR DEPORTISTA EN BASE DE DATOS */
	public boolean borrarDeportista(int id_deportista) {
		try {
			conexion = new ConexionBD();       
			String consulta = "DELETE FROM Deportista WHERE id_deportista = ?";
	
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
	
	/** ACTUALIZAR DEPORTISTA EN BASE DE DATOS */
	public boolean actualizarDeportista(int id_deportista, String nombre, String sexo, int peso, int altura) {
		try {
			conexion = new ConexionBD();       
			String consulta = "UPDATE Deportista SET nombre = ?, sexo = ?, peso = ?, altura = ? WHERE id_deportista = ?";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setString(1, nombre);
	        pstmt.setString(2, sexo);
	        pstmt.setInt(3, peso);
	        pstmt.setInt(4, altura);
	        pstmt.setInt(5, id_deportista);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** OBTENER EL ID DEL DEPORTISTA SOLICITADO */
	public int dameIdDeDeportista(String nombre) {
	    try {
	        conexion = new ConexionBD();
	        String consulta = "SELECT id_deportista FROM Deportista WHERE nombre = ?";
	        PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setString(1, nombre);

	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            return rs.getInt("id_deportista");
	        }

	        rs.close();
	        conexion.closeConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1;
	}
	
	/** VERIFICAR SI EXISTE EL DEPORTISTA EN BASE DE DATOS */
	public boolean existeDeportista(String nombre) {
	    try {
	        conexion = new ConexionBD();
	        String consulta = "SELECT * FROM Deportista WHERE nombre = ?";
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