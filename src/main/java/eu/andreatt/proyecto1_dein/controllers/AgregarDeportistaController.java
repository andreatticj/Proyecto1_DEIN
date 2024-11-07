package eu.andreatt.proyecto1_dein.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import eu.andreatt.proyecto1_dein.dao.DeportistaDao;
import eu.andreatt.proyecto1_dein.model.Deportista;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AgregarDeportistaController implements Initializable{

    /** VARIABLES */
    @FXML
    private Button botonCancelar;

    @FXML
    private Button botonGuardar;

    @FXML
    private TextField textFieldAltura;

    @FXML
    private TextField textFieldNombre;

    @FXML
    private TextField textFieldPeso;

    @FXML
    private TextField textFieldSexo;

    private ObservableList<Deportista>deportistasExistentes;

    private boolean guardando = false;

    private ResourceBundle bundle;

    private Deportista deportista;

    /** INIT ATTRIBUTES - RECIBIR DATOS DE APLICACIÓN */
    public void initAttributtes(ObservableList<Deportista> deportistasExistentes) {
        this.deportistasExistentes = deportistasExistentes;
    }

    /** INIT ATTRIBUTES - RECIBIR DATOS DE APLICACIÓN */
    public void initAttributtes(ObservableList<Deportista> deportistasExistentes, Deportista deportista) {
        this.deportistasExistentes = deportistasExistentes;
        this.deportista = deportista;
        textFieldNombre.setText(deportista.getNombre());
        textFieldAltura.setText(deportista.getAltura()+"");
        textFieldSexo.setText(deportista.getSexo());
        textFieldPeso.setText(deportista.getPeso()+"");
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
        String errores= validarDatos();
        if (!errores.isEmpty()){
            String deportista = textFieldNombre.getText().trim();
            //Comprobar si existe deportista
            if(deportista.length()>0) {
                boolean existeDeportista = new DeportistaDao().existeDeportista(deportista);
                if(existeDeportista) {
                    //Mensaje de alerta
                    generarVentana(AlertType.ERROR, bundle.getString("agregarDeportistaIncorrecto"), "ERROR");
                }else {
                    //Modificar o añadir
                    if(guardando) {
                        if(validarDatos().isEmpty()) {
                            //Actualizar deportista
                            new DeportistaDao().actualizarDeportista(new DeportistaDao().dameIdDeDeportista(this.deportista.getNombre()), textFieldNombre.getText(), textFieldSexo.getText(), Integer.parseInt(textFieldPeso.getText()), Integer.parseInt(textFieldAltura.getText()));
                            deportistasExistentes.set(deportistasExistentes.indexOf(this.deportista), new Deportista(new DeportistaDao().dameIdDeDeportista(this.deportista.getNombre()), textFieldNombre.getText(), textFieldSexo.getText(), Integer.parseInt(textFieldPeso.getText()), Integer.parseInt(textFieldAltura.getText())));

                            //Mensaje de alerta
                            generarVentana(AlertType.INFORMATION, bundle.getString("editarDeportistaCorrecto"), "INFO");
                        }else {
                            //Mensaje de alerta
                            generarVentana(AlertType.ERROR, validarDatos(), "ERROR");
                        }
                    }else {
                       if(validarDatos().isEmpty()) {
                            //Añadir deportista
                            new DeportistaDao().insertarDeportista(textFieldNombre.getText(), textFieldSexo.getText(), Integer.parseInt(textFieldPeso.getText()), Integer.parseInt(textFieldAltura.getText()));
                            deportistasExistentes.add(new Deportista(0, textFieldNombre.getText(), textFieldSexo.getText(), Integer.parseInt(textFieldPeso.getText()), Integer.parseInt(textFieldAltura.getText())));

                            //Mensaje de alerta
                            generarVentana(AlertType.INFORMATION, bundle.getString("agregarDeportistaCorrecto"), "INFO");
                        }else {
                            generarVentana(AlertType.ERROR, validarDatos(), "ERROR");
                        }
                    }
                }
            }else{
            //Cerrar ventana modal
                generarVentana(AlertType.ERROR, validarDatos(), "ERROR");
//                Stage stage = (Stage) botonCancelar.getScene().getWindow();
//                stage.close();
            }
        }
    }

    /** VALIDAR DATOS INTRODUCIDOS */
    private String validarDatos() {
        String errores = "";

        // Validar nombre (no debe estar vacío)
        String nombre = textFieldNombre.getText().trim();
        if (nombre.isEmpty()) {
            errores += bundle.getString("errorTexto") + " " + bundle.getString("labelNombre") + "\n";
        } else if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {  // Validación de solo letras y espacios
            errores += bundle.getString("errorNombreInvalido") + " " + bundle.getString("labelNombre") + "\n";
        }

        // Validar sexo (comprobar que no esté vacío y que sea un valor válido)
        String sexo = textFieldSexo.getText().trim();  // Usar trim para eliminar espacios extra
        if (sexo.isEmpty()) {
            errores += bundle.getString("errorTextoSexo") + " " + bundle.getString("labelSexo") + "\n";
        } else if (!sexo.equalsIgnoreCase("m") && !sexo.equalsIgnoreCase("f")) {
            errores += bundle.getString("errorSexoInvalido") + " " + bundle.getString("labelSexo") + "\n";  // Agregar un error por valor inválido
        }

        // Validar peso (número positivo)
        if (textFieldPeso.getText().isEmpty()) {
            errores += bundle.getString("errorTexto") + " " + bundle.getString("labelPeso") + "\n";
        } else if (!esNumeroEntero(textFieldPeso.getText()) || Integer.parseInt(textFieldPeso.getText()) <= 0) {
            errores += bundle.getString("errorNumeroPositivo") + " " + bundle.getString("labelPeso") + "\n";
        }

        // Validar altura (número positivo)
        if (textFieldAltura.getText().isEmpty()) {
            errores += bundle.getString("errorTexto") + " " + bundle.getString("labelAltura") + "\n";
        } else if (!esNumeroEntero(textFieldAltura.getText()) || Integer.parseInt(textFieldAltura.getText()) <= 0) {
            errores += bundle.getString("errorNumeroPositivo") + " " + bundle.getString("labelAltura") + "\n";
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