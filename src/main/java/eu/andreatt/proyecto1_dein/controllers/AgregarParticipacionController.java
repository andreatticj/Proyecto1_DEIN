package eu.andreatt.proyecto1_dein.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import eu.andreatt.proyecto1_dein.dao.DeportistaDao;
import eu.andreatt.proyecto1_dein.dao.EquipoDao;
import eu.andreatt.proyecto1_dein.dao.EventoDao;
import eu.andreatt.proyecto1_dein.dao.ParticipacionDao;
import eu.andreatt.proyecto1_dein.model.Deportista;
import eu.andreatt.proyecto1_dein.model.Equipo;
import eu.andreatt.proyecto1_dein.model.InformacionEvento;
import eu.andreatt.proyecto1_dein.model.InformacionParticipacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class AgregarParticipacionController implements Initializable{

    /** VARIABLES */
    @FXML
    private Button botonCancelar;

    @FXML
    private Button botonGuardar;

    @FXML
    private ComboBox<Deportista> comboDeportistas;

    @FXML
    private ComboBox<Equipo> comboEquipos;

    @FXML
    private ComboBox<InformacionEvento> comboEventos;

    @FXML
    private ComboBox<String> comboMedallas;

    @FXML
    private TextField textFieldEdad;

    private ObservableList<InformacionParticipacion> participacionesExistentes;

    private boolean guardando = false;

    private ResourceBundle bundle;

    private InformacionParticipacion participacion;

    private ObservableList<Deportista> elementosDeportista;
    private ObservableList<InformacionEvento> elementosEvento;
    private ObservableList<Equipo> elementosEquipo;
    private ObservableList<String> elementosMedallas;

    private static String[] medallas = new String[]{"NA", "Gold", "Silver", "Bronze"};

    /** INIT ATTRIBUTES - RECIBIR DATOS DE APLICACIÓN */
    public void initAttributtes(ObservableList<InformacionParticipacion> participacionesExistentes) {
        this.participacionesExistentes = participacionesExistentes;
    }

    /** INIT ATTRIBUTES - RECIBIR DATOS DE APLICACIÓN */
    public void initAttributtes(ObservableList<InformacionParticipacion> participacionesExistentes, InformacionParticipacion participacion) {
        this.participacionesExistentes = participacionesExistentes;
        this.participacion = participacion;

        guardando = true;
    }

    /** INITIALIZE - INICIALIZAR CARGA DE DATOS */
    public void initialize(URL arg0, ResourceBundle arg1) {
        //Instanciar bundle para idiomas
        bundle = arg1;

        //Cargar combos
        elementosDeportista = new DeportistaDao().cargarDeportistas();
        comboDeportistas.setItems(elementosDeportista);
        comboDeportistas.setValue(elementosDeportista.get(0));

        elementosEvento = new EventoDao().cargarEventos();
        comboEventos.setItems(elementosEvento);
        comboEventos.setValue(elementosEvento.get(0));

        elementosEquipo = new EquipoDao().cargarEquipos();
        comboEquipos.setItems(elementosEquipo);
        comboEquipos.setValue(elementosEquipo.get(0));

        elementosMedallas = FXCollections.observableArrayList(medallas);
        comboMedallas.setItems(elementosMedallas);
        comboMedallas.setValue(elementosMedallas.get(0));
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
        String deportistaNombre = comboDeportistas.getSelectionModel().getSelectedItem().getNombre();
        String eventoNombre = comboEventos.getSelectionModel().getSelectedItem().getNombre();
        String equipoNombre = comboEquipos.getSelectionModel().getSelectedItem().getNombre();
        int edad = Integer.parseInt(textFieldEdad.getText());
        String medalla = comboMedallas.getSelectionModel().getSelectedItem();

        // Validar que la edad sea un número
        if (esNumeroEntero(textFieldEdad.getText())) {
            // Obtener los IDs usando los métodos de DAO
            int deportistaId = new DeportistaDao().dameIdDeDeportista(deportistaNombre);
            int eventoId = new EventoDao().dameIdDeEvento(eventoNombre);
            int equipoId = new EquipoDao().dameIdDeEquipo(equipoNombre);

            // Modificar o añadir
            if (guardando) {
                // Actualizar la participación existente con los IDs correctos
                participacionesExistentes.set(participacionesExistentes.indexOf(this.participacion),
                        new InformacionParticipacion(deportistaNombre, eventoNombre, equipoNombre, edad, medalla));

                // Aquí puedes hacer lo que necesites con la base de datos si quieres actualizarla.
                // Por ejemplo, actualizar los datos de la participación en la base de datos

                // Mensaje de alerta
                generarVentana(AlertType.INFORMATION, bundle.getString("editarParticipacionCorrecto"), "INFO");
            } else {
                // Añadir nueva participación a la lista y base de datos
                participacionesExistentes.add(new InformacionParticipacion(deportistaNombre, eventoNombre, equipoNombre, edad, medalla));
                new ParticipacionDao().insertarParticipacion(deportistaId, eventoId, equipoId, edad, medalla);

                // Mensaje de alerta
                generarVentana(AlertType.INFORMATION, bundle.getString("agregarParticipacionCorrecto"), "INFO");
            }

            // Cerrar la ventana modal si todo está correcto
            Stage stage = (Stage) botonCancelar.getScene().getWindow();
            stage.close();
        } else {
            generarVentana(AlertType.INFORMATION, bundle.getString("errorNumero") + " " + bundle.getString("labelEdad"), "INFO");
        }
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