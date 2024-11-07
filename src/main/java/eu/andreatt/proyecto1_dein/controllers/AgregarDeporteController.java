package eu.andreatt.proyecto1_dein.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import eu.andreatt.proyecto1_dein.dao.DeporteDao;
import eu.andreatt.proyecto1_dein.model.Deporte;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AgregarDeporteController implements Initializable{

    /** VARIABLES */
    @FXML
    private Button botonCancelar;

    @FXML
    private Button botonGuardar;

    @FXML
    private TextField textFieldNombre;

    private ResourceBundle bundle;

    private ObservableList<Deporte> deportesExistentes;

    private boolean guardando = false;

    private Deporte deporte;

    /** INIT ATTRIBUTES - RECIBIR DATOS DE APLICACIÓN */
    public void initAttributtes(ObservableList<Deporte> deportesExistentes) {
        this.deportesExistentes = deportesExistentes;
    }

    /** INIT ATTRIBUTES - RECIBIR DATOS DE APLICACIÓN */
    public void initAttributtes(ObservableList<Deporte> deportesExistentes, Deporte deporte) {
        this.deportesExistentes = deportesExistentes;
        this.deporte = deporte;
        textFieldNombre.setText(deporte.getNombre());
        guardando = true;
    }

    /** INITIALIZE - INICIALIZAR CARGA DE DATOS */
    public void initialize(URL arg0, ResourceBundle arg1) {
        //Instanciar bundle para idiomas
        bundle = arg1;
    }

    /** EVENTO - AL PULSAR CANCELAR */
    @FXML
    void actionCancelar(ActionEvent event) {
        //Cerrar ventana modal
        Stage stage = (Stage) botonCancelar.getScene().getWindow();
        stage.close();
    }

    /** EVENTO - AL PULSAR GUARDAR */
    @FXML
    void actionGuardar(ActionEvent event) {
        String deporte = textFieldNombre.getText().trim();

        //Comprobar si existe deporte
        if(deporte.length()>0) {
            boolean existeDeporte = new DeporteDao().existeDeporte(deporte);
            if(existeDeporte) {
                //Mensaje de alerta
                generarVentana(AlertType.ERROR, bundle.getString("agregarDeporteIncorrecto"), "ERROR");
            }else {
                //Modificar o añadir
                if(guardando) {
                    //Actualizar deporte
                    new DeporteDao().actualizarDeporte(new DeporteDao().dameIdDeDeporte(this.deporte.getNombre()), deporte);
                    deportesExistentes.set(deportesExistentes.indexOf(this.deporte), new Deporte(new DeporteDao().dameIdDeDeporte(this.deporte.getNombre()), deporte));

                    //Mensaje de alerta
                    generarVentana(AlertType.INFORMATION, bundle.getString("editarDeporteCorrecto"), "INFO");
                }else {
                    //Añadir deporte
                    new DeporteDao().insertarDeporte(new DeporteDao().dameMaxIdDeportes()+1, deporte);
                    deportesExistentes.add(new Deporte(new DeporteDao().dameMaxIdDeportes()+1, deporte));

                    //Mensaje de alerta
                    generarVentana(AlertType.INFORMATION, bundle.getString("agregarDeporteCorrecto"), "INFO");
                }

                //Cerrar ventana modal
                Stage stage = (Stage) botonCancelar.getScene().getWindow();
                stage.close();
            }
        }
    }

    /** GENERAR VENTANA DE ALERTA */
    private void generarVentana(AlertType tipoDeAlerta, String mensaje, String title) {
        Alert alerta = new Alert(tipoDeAlerta);
        alerta.setContentText(mensaje);
        alerta.setHeaderText(null);
        alerta.setTitle(title);
        alerta.showAndWait();
    }
}