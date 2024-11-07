package eu.andreatt.proyecto1_dein.application;

import java.util.Locale;
import java.util.ResourceBundle;

import eu.andreatt.proyecto1_dein.utils.Propiedades;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;


public class MenuPrincipal extends Application{
    @Override
    public void start(Stage primaryStage) {
        try {
            //Multilingue
            String idioma = Propiedades.getValor("idioma");
            String region = Propiedades.getValor("region");
            Locale.setDefault(new Locale(idioma, region));
            ResourceBundle bundle = ResourceBundle.getBundle("/eu/andreatt/proyecto1_dein/idiomas/messages");

            //Logo
            Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/proyecto1_dein/images/antorcha.png"));
            primaryStage.getIcons().add(icon);

            //Escena principal
            GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("/eu/andreatt/proyecto1_dein/fxml/MenuPrincipal.fxml"),bundle);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/eu/andreatt/proyecto1_dein/css/application.css").toExternalForm());

            //Primary Stage
            primaryStage.setTitle(bundle.getString("tituloPrincipal"));
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}