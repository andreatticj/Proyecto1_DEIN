package eu.andreatt.proyecto1_dein.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPrincipal extends Application {
    /**
     * Metodo de inicio de la aplicación JavaFX.
     * Carga el archivo FXML para la interfaz de inicio de sesión, establece el título de la ventana
     * y la muestra.
     *
     * @param stage La ventana principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuPrincipal.class.getResource("/eu/andreatt/ejerciciol_dein/fxml/eu.andreatt.proyecto1_dein.application.MenuPrincipal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Configuración de la ventana
        stage.setTitle("OLIMPIADAS");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metodo principal de la aplicación.
     * Lanza la aplicación JavaFX.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        launch();
    }
}
