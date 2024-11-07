package eu.andreatt.proyecto1_dein.bbdd;


import eu.andreatt.proyecto1_dein.utils.Propiedades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

/**
 * Clase que gestiona la conexión con la base de datos.
 */
public class ConexionBD {

    /** Conexión activa con la base de datos. */
    private Connection conexion;

    /**
     * Constructor que establece una conexión con la base de datos.
     */
    public ConexionBD() throws SQLException {
        String url = Propiedades.getValor("url") + "?serverTimezone=" + TimeZone.getDefault().getID();
        String user = Propiedades.getValor("user");
        String password = Propiedades.getValor("password");

        //System.out.println("Conectando a la base de datos con URL: " + url + ", Usuario: " + user);
        conexion = DriverManager.getConnection(url, user, password);
        conexion.setAutoCommit(true);
    }

    /**
     * Obtiene la conexión actual con la base de datos.
     */
    public Connection getConexion() {
        return conexion;
    }

    /**
     * Cierra la conexión con la base de datos.
     */
    public void closeConnection() throws SQLException {
        conexion.close();
    }
}

