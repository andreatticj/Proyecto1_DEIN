package eu.andreatt.proyecto1_dein.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eu.andreatt.proyecto1_dein.bbdd.ConexionBD;
import eu.andreatt.proyecto1_dein.model.Deporte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DeporteDao {
    private ConexionBD conexion;

    /** CARGAR LOS DEPORTES EXISTENTES EN BASE DE DATOS */
    public ObservableList<Deporte> cargarDeportes() {

        ObservableList<Deporte> deporte = FXCollections.observableArrayList();
        try {
            conexion = new ConexionBD();
            String consulta = "SELECT * FROM Deporte";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int idDeporte = rs.getInt("id_deporte");
                String nombre = rs.getString("nombre");

                deporte.add(new Deporte(idDeporte, nombre));
            }
            rs.close();
            conexion.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deporte;
    }

    /** CARGAR LOS NOMBRES DE DEPORTES EN BASE DE DATOS */
    public ObservableList<String> cargarNombresDeDeportes() {

        ObservableList<String> nombre = FXCollections.observableArrayList();
        try {
            conexion = new ConexionBD();
            String consulta = "SELECT DISTINCT nombre as nombre FROM Deporte;";
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

    /** INSERTAR DEPORTE EN BASE DE DATOS */
    public boolean insertarDeporte(int id_deporte, String nombre) {
        try {
            conexion = new ConexionBD();
            String consulta = "INSERT INTO Deporte (id_deporte, nombre) VALUES (?, ?)";

            PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
            pstmt.setInt(1, id_deporte);
            pstmt.setString(2, nombre);

            pstmt.executeUpdate();
            conexion.closeConnection();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /** BORRAR DEPORTE EN BASE DE DATOS */
    public boolean borrarDeporte(int id_deporte) {
        try {
            conexion = new ConexionBD();
            String consulta = "DELETE FROM Deporte WHERE id_deporte = ?";

            PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
            pstmt.setInt(1, id_deporte);

            pstmt.executeUpdate();
            conexion.closeConnection();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /** ACTUALIZR DEPORTE EN BASE DE DATOS */
    public boolean actualizarDeporte(int id_deporte, String nombre) {
        try {
            conexion = new ConexionBD();
            String consulta = "UPDATE Deporte SET nombre = ? WHERE id_deporte = ?";

            PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
            pstmt.setString(1, nombre);
            pstmt.setInt(2, id_deporte);

            pstmt.executeUpdate();
            conexion.closeConnection();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /** OBTENER EL ID MÁS GRANDE DE LOS DEPORTES EN BASE DE DATOS */
    public int dameMaxIdDeportes() {
        try {
            conexion = new ConexionBD();
            String consulta = "SELECT MAX(id_deporte) AS max_id FROM Deporte";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("max_id");
            }

            rs.close();
            conexion.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /** OBTENER EL ID DEL DEPORTE SOLICITADO */
    public int dameIdDeDeporte(String nombre) {
        try {
            conexion = new ConexionBD();
            String consulta = "SELECT id_deporte FROM Deporte WHERE nombre = ?";
            PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
            pstmt.setString(1, nombre);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_deporte");
            }

            rs.close();
            conexion.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    /** VERIFICAR SI EXISTE EL DEPORTE EN BASE DE DATOS */
    public boolean existeDeporte(String nombre) {
        try {
            conexion = new ConexionBD();
            String consulta = "SELECT * FROM Deporte WHERE nombre = ?";
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