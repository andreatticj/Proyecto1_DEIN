package eu.andreatt.proyecto1_dein.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import eu.andreatt.proyecto1_dein.dao.DeporteDao;
import eu.andreatt.proyecto1_dein.dao.EventoDao;
import eu.andreatt.proyecto1_dein.dao.OlimpiadaDao;
import eu.andreatt.proyecto1_dein.model.Deporte;
import eu.andreatt.proyecto1_dein.model.InformacionEvento;
import eu.andreatt.proyecto1_dein.model.Olimpiada;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AgregarEventoController implements Initializable {

    /** VARIABLES */
    @FXML
    private Button botonCancelar;

    @FXML
    private Button botonGuardar;

    @FXML
    private ComboBox<Deporte> comboDeportes;

    @FXML
    private ComboBox<Olimpiada> comboOlimpiadas;

    @FXML
    private TextField comboNombres;

    private ObservableList<InformacionEvento> eventosExistentes;

    private ObservableList<Deporte> elementosDeportes;
    private ObservableList<Olimpiada> elementosOlimpiadas;

    private boolean guardando = false;

    private ResourceBundle bundle;

    private InformacionEvento evento;

    /** INIT ATTRIBUTES - RECIBIR DATOS DE APLICACIÓN */
    public void initAttributtes(ObservableList<InformacionEvento> eventosExistentes) {
        this.eventosExistentes = eventosExistentes;
    }

    /** INIT ATTRIBUTES - RECIBIR DATOS DE APLICACIÓN */
    public void initAttributtes(ObservableList<InformacionEvento> eventosExistentes, InformacionEvento evento) {
        this.eventosExistentes = eventosExistentes;
        this.evento = evento;
        comboNombres.setText(evento.getNombre());
        comboOlimpiadas.setValue(elementosOlimpiadas.get(0));
        comboDeportes.setValue(elementosDeportes.get(0));

        guardando = true;
    }

    /** INITIALIZE - INICIALIZAR CARGA DE DATOS */
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Instanciar bundle para idiomas
        bundle = arg1;

        // Cargar combos
        elementosOlimpiadas = new OlimpiadaDao().cargarOlimpiadas();
        comboOlimpiadas.setItems(elementosOlimpiadas);
        comboOlimpiadas.setValue(elementosOlimpiadas.get(0));

        elementosDeportes = new DeporteDao().cargarDeportes();
        comboDeportes.setItems(elementosDeportes);
        comboDeportes.setValue(elementosDeportes.get(0));
    }

    /** EVENTO - AL PULSAR CANCELAR */
    @FXML
    void actionCancelar(ActionEvent event) {
        // Cerrar ventana modal
        Stage stage = (Stage) botonCancelar.getScene().getWindow();
        stage.close();
    }

    /** EVENTO - AL PULSAR GUARDAR */
    @FXML
    void actionGuardar(ActionEvent event) {
        String errores = validarDatos();
        if (!errores.isEmpty()){
        if (guardando) {
            int id_evento = new EventoDao().dameIdDeEvento(this.evento.getNombre());
            boolean actualizado = new EventoDao().actualizarEvento(
                    id_evento,
                    comboNombres.getText(),
                    comboOlimpiadas.getSelectionModel().getSelectedItem().getId_olimpiada(),
                    comboDeportes.getSelectionModel().getSelectedItem().getId_deporte()
            );

            if (actualizado) {
                eventosExistentes.set(eventosExistentes.indexOf(this.evento), new InformacionEvento(
                        comboNombres.getText(),
                        comboOlimpiadas.getSelectionModel().getSelectedItem().getNombre(),
                        comboDeportes.getSelectionModel().getSelectedItem().getNombre()
                ));
                generarVentana(AlertType.INFORMATION, bundle.getString("editarEventoCorrecto"), "INFO");
            } else {
                generarVentana(AlertType.ERROR, bundle.getString("errorActualizarEvento"), "ERROR");
            }

        } else {
            String nombreEvento = comboNombres.getText();
            int id_olimpiada = comboOlimpiadas.getSelectionModel().getSelectedItem().getId_olimpiada();
            int id_deporte = comboDeportes.getSelectionModel().getSelectedItem().getId_deporte();
            boolean insertado = new EventoDao().insertarEvento(nombreEvento, id_olimpiada, id_deporte);

            if (insertado) {
                eventosExistentes.add(new InformacionEvento(
                        nombreEvento,
                        comboOlimpiadas.getSelectionModel().getSelectedItem().getNombre(),
                        comboDeportes.getSelectionModel().getSelectedItem().getNombre()
                ));
                generarVentana(AlertType.INFORMATION, bundle.getString("agregarEventoCorrecto"), "INFO");
            } else {
                generarVentana(AlertType.ERROR, bundle.getString("errorInsertarEvento"), "ERROR");
            }
        }
        }else {
            generarVentana(AlertType.ERROR, validarDatos(), "ERROR");
        }

    }

    /** VALIDAR DATOS INTRODUCIDOS */
    private String validarDatos() {
        String errores = "";

        //Validar temporada
        if(comboNombres.getText().isEmpty()) {
            errores+=bundle.getString("errorTexto")+" "+bundle.getString("labelNombre")+"\n";
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
