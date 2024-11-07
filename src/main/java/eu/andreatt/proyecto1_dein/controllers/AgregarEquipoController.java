package eu.andreatt.proyecto1_dein.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import eu.andreatt.proyecto1_dein.dao.EquipoDao;
import eu.andreatt.proyecto1_dein.model.Equipo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AgregarEquipoController implements Initializable {

    /** VARIABLES */
    @FXML
    private Button botonCancelar;

    @FXML
    private Button botonGuardar;

    @FXML
    private TextField textFieldIniciales;

    @FXML
    private TextField textFieldNombre;

    private ObservableList<Equipo> equiposExistentes;

    private boolean guardando = false;

    private ResourceBundle bundle;

    private Equipo equipo;

    /** INIT ATTRIBUTES - RECIBIR DATOS DE APLICACIÓN */
    public void initAttributtes(ObservableList<Equipo> equiposExistentes) {
        this.equiposExistentes = equiposExistentes;
    }

    /** INIT ATTRIBUTES - RECIBIR DATOS DE APLICACIÓN */
    public void initAttributtes(ObservableList<Equipo> equiposExistentes, Equipo equipo) {
        this.equiposExistentes = equiposExistentes;
        this.equipo = equipo;
        textFieldNombre.setText(equipo.getNombre());
        textFieldIniciales.setText(equipo.getIniciales());
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
        String errores = validarDatos();
        if (!errores.isEmpty()){
        String equipo = textFieldNombre.getText().trim();

        //Validar errores
        if(equipo.length()>0) {
            boolean existeEquipo = new EquipoDao().existeEquipo(equipo);
            if(existeEquipo) {
                //Mensaje de alerta
                generarVentana(AlertType.ERROR, bundle.getString("agregarEquipoIncorrecto"), "ERROR");
            }else {
                //Modificar o añadir
                if(guardando) {
                    //Validar textos rellenos
                    if(!textFieldIniciales.getText().isEmpty()) {
                        //Actualizar equipo
                        new EquipoDao().actualizarEquipo(new EquipoDao().dameIdDeEquipo(this.equipo.getNombre()), textFieldNombre.getText(), textFieldIniciales.getText());
                        equiposExistentes.set(equiposExistentes.indexOf(this.equipo), new Equipo(new EquipoDao().dameIdDeEquipo(this.equipo.getNombre()), textFieldNombre.getText(), textFieldIniciales.getText()));

                        //Mensaje de alerta
                        generarVentana(AlertType.INFORMATION, bundle.getString("editarEquipoCorrecto"), "INFO");
                    }else {
                        generarVentana(AlertType.INFORMATION, bundle.getString("errorTexto")+" "+bundle.getString("labelIniciales"), "INFO");
                    }
                }else {
                    if(!textFieldIniciales.getText().isEmpty()) {
                        //Añadir equipo
                        new EquipoDao().insertarEquipo(textFieldNombre.getText(), textFieldIniciales.getText());
                        equiposExistentes.add(new Equipo(0, textFieldNombre.getText(), textFieldIniciales.getText()));

                        //Mensaje de alerta
                        generarVentana(AlertType.INFORMATION, bundle.getString("agregarEquipoCorrecto"), "INFO");
                    }else {
                        generarVentana(AlertType.INFORMATION, bundle.getString("errorTexto")+" "+bundle.getString("labelIniciales"), "INFO");
                    }
                }

            }
        }else{
            generarVentana(AlertType.ERROR, validarDatos(), "ERROR");
        }
        }
    }

    /** VALIDAR DATOS INTRODUCIDOS */
    private String validarDatos() {
        String errores = "";

        // Validar nombre del equipo
        if (textFieldNombre.getText().isEmpty()) {
            errores += bundle.getString("errorTexto") + " " + bundle.getString("labelNombre") + "\n";
        }

        // Validar iniciales del equipo
        if (textFieldIniciales.getText().isEmpty()) {
            errores += bundle.getString("errorTexto") + " " + bundle.getString("labelIniciales") + "\n";
        }

        // Validar si el equipo ya existe en la base de datos
        String nombreEquipo = textFieldNombre.getText().trim();
        boolean existeEquipo = new EquipoDao().existeEquipo(nombreEquipo);
        if (existeEquipo && !guardando) {
            errores += bundle.getString("agregarEquipoIncorrecto") + "\n";
        }

        return errores;
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