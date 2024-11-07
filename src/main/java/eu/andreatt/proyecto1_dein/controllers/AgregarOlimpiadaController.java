package eu.andreatt.proyecto1_dein.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import eu.andreatt.proyecto1_dein.dao.OlimpiadaDao;
import eu.andreatt.proyecto1_dein.model.Olimpiada;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AgregarOlimpiadaController implements Initializable{

    /** VARIABLES */
    @FXML
    private Button botonCancelar;

    @FXML
    private Button botonGuardar;

    @FXML
    private TextField textFieldAnio;

    @FXML
    private TextField textFieldCiudad;

    @FXML
    private TextField textFieldNombre;

    @FXML
    private TextField textFieldTemporada;

    private ObservableList<Olimpiada> olimpiadasExistentes;

    private boolean guardando = false;

    private ResourceBundle bundle;

    private Olimpiada olimpiada;

    /** INIT ATTRIBUTES - RECIBIR DATOS DE APLICACIÓN */
    public void initAttributtes(ObservableList<Olimpiada> olimpiadasExistentes) {
        this.olimpiadasExistentes = olimpiadasExistentes;
    }

    /** INIT ATTRIBUTES - RECIBIR DATOS DE APLICACIÓN */
    public void initAttributtes(ObservableList<Olimpiada> olimpiadasExistentes, Olimpiada olimpiada) {
        this.olimpiadasExistentes = olimpiadasExistentes;
        this.olimpiada = olimpiada;
        textFieldNombre.setText(olimpiada.getNombre());
        textFieldAnio.setText(olimpiada.getAnio()+"");
        textFieldCiudad.setText(olimpiada.getCiudad());
        textFieldTemporada.setText(olimpiada.getTemporada());
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
        if (errores.isEmpty()){
        String olimpiada = textFieldNombre.getText().trim();

        //Validar errores
        if(olimpiada.length()>0) {
            boolean existeOlimpiada = new OlimpiadaDao().existeOlimpiada(olimpiada);
            if(existeOlimpiada) {
                //Mensaje de alerta
                generarVentana(AlertType.ERROR, bundle.getString("agregarOlimpiadaIncorrecto"), "ERROR");
            }else {
                //Modificar o añadir
                if(guardando) {
                    if(validarDatos().isEmpty()) {
                        //Actualizar olimpiada
                        new OlimpiadaDao().actualizarOlimpiada(new OlimpiadaDao().dameIdDeOlimpiada(this.olimpiada.getNombre()), textFieldNombre.getText(), Integer.parseInt(textFieldAnio.getText()), textFieldTemporada.getText(), textFieldCiudad.getText());
                        olimpiadasExistentes.set(olimpiadasExistentes.indexOf(this.olimpiada), new Olimpiada(new OlimpiadaDao().dameIdDeOlimpiada(this.olimpiada.getNombre()), textFieldNombre.getText(), Integer.parseInt(textFieldAnio.getText()), textFieldTemporada.getText(), textFieldCiudad.getText()));

                        //Mensaje de alerta
                        generarVentana(AlertType.INFORMATION, bundle.getString("editarOlimpiadaCorrecto"), "INFO");
                    }else {
                        generarVentana(AlertType.INFORMATION, validarDatos(), "INFO");
                    }
                }else {
                    if(validarDatos().isEmpty()) {
                        //Añadir olimpiada
                        new OlimpiadaDao().insertarOlimpiada(textFieldNombre.getText(), Integer.parseInt(textFieldAnio.getText()), textFieldTemporada.getText(), textFieldCiudad.getText());
                        olimpiadasExistentes.add(new Olimpiada(0, textFieldNombre.getText(), Integer.parseInt(textFieldAnio.getText()), textFieldTemporada.getText(), textFieldCiudad.getText()));

                        //Mensaje de alerta
                        generarVentana(AlertType.INFORMATION, bundle.getString("agregarOlimpiadaCorrecto"), "INFO");
                    }else {
                        generarVentana(AlertType.INFORMATION, validarDatos(), "INFO");
                    }
                }

            }
        }
    }
        else{
            generarVentana(AlertType.ERROR, validarDatos(), "ERROR");
        }
    }

    /** VALIDAR DATOS INTRODUCIDOS */
    private String validarDatos() {
        String errores = "";

        //Validar temporada
        if(textFieldNombre.getText().isEmpty()) {
            errores+=bundle.getString("errorTexto")+" "+bundle.getString("labelNombre")+"\n";
        }

        //Validar temporada
        if(textFieldTemporada.getText().isEmpty()) {
            errores+=bundle.getString("errorTexto")+" "+bundle.getString("labelTemporada")+"\n";
        }

        //Validar ciudad
        if(textFieldCiudad.getText().isEmpty()) {
            errores+=bundle.getString("errorTexto")+" "+bundle.getString("labelCiudad")+"\n";
        }

        //Validar año
        if(textFieldAnio.getText().isEmpty()) {
            errores+=bundle.getString("errorTexto")+" "+bundle.getString("labelAnio")+"\n";
        }else if(!esNumeroEntero(textFieldAnio.getText())) {
            errores+=bundle.getString("errorTextoAnio")+" "+bundle.getString("labelAnio")+"\n";
        }

        return errores;
    }

    /** VALIDAR SI ES UN NUMERO */
    private static boolean esNumeroEntero(String valor) {
        return valor.matches("-?\\d+");
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