package eu.andreatt.proyecto1_dein.controllers;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;


import eu.andreatt.proyecto1_dein.dao.*;
import eu.andreatt.proyecto1_dein.model.*;
import eu.andreatt.proyecto1_dein.utils.Propiedades;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MenuPrincipalController implements Initializable{

    /** VARIABLES */
    @FXML
    private Button botonAgregarDeporte;

    @FXML
    private Button botonAgregarDeportista;

    @FXML
    private Button botonAgregarEquipo;

    @FXML
    private Button botonAgregarEvento;

    @FXML
    private Button botonAgregarOlimpiada;

    @FXML
    private Button botonAgregarParticipacion;

    @FXML
    private Button botonBorrarDeporte;

    @FXML
    private Button botonBorrarDeportista;

    @FXML
    private Button botonBorrarEquipo;

    @FXML
    private Button botonBorrarEvento;

    @FXML
    private Button botonBorrarOlimpiada;

    @FXML
    private Button botonBorrarParticipacion;

    @FXML
    private Button botonDeporte;

    @FXML
    private Button botonDeportista;

    @FXML
    private Button botonEditarDeporte;

    @FXML
    private Button botonEditarDeportista;

    @FXML
    private Button botonEditarEquipo;

    @FXML
    private Button botonEditarEvento;

    @FXML
    private Button botonEditarOlimpiada;

    @FXML
    private Button botonEditarParticipacion;

    @FXML
    private Button botonEquipo;

    @FXML
    private Button botonEvento;

    @FXML
    private Button botonOlimpiada;

    @FXML
    private Button botonParticipacion;

    @FXML
    private Label labelTablaActual;

    @FXML
    private TextField textFieldFiltro;

    @FXML
    private GridPane panelDeporte;

    @FXML
    private GridPane panelDeportista;

    @FXML
    private GridPane panelEquipo;

    @FXML
    private GridPane panelEvento;

    @FXML
    private GridPane panelOlimpiada;

    @FXML
    private GridPane panelParticipacion;

    @FXML
    private TableView<Deporte> tableDeporte;

    @FXML
    private TableView<Deportista> tableDeportista;

    @FXML
    private TableView<Equipo> tableEquipo;

    @FXML
    private TableView<Olimpiada> tableOlimpiada;

    @FXML
    private TableView<InformacionEvento> tableEvento;

    @FXML
    private TableView<InformacionParticipacion> tableParticipacion;

    @FXML
    private TableColumn<Deporte, String> columnNombreDeporte;

    @FXML
    private TableColumn<Deportista, Integer> columnAlturaDeportista;

    @FXML
    private TableColumn<Deportista, Integer> columnPesoDeportista;

    @FXML
    private TableColumn<Deportista, String> columnSexoDeportista;

    @FXML
    private TableColumn<Deportista, String> columnNombreDeportista;

    @FXML
    private TableColumn<Equipo, String> columnNombreEquipo;

    @FXML
    private TableColumn<Equipo, String> columnInicialesEquipo;

    @FXML
    private TableColumn<Olimpiada, String> columnNombreOlimpiada;

    @FXML
    private TableColumn<Olimpiada, String> columnTemporadaOlimpiada;

    @FXML
    private TableColumn<Olimpiada, String> columnCiudadOlimpiada;

    @FXML
    private TableColumn<Olimpiada, Integer> columnAnioOlimpiada;

    @FXML
    private TableColumn<InformacionEvento, String> columnNombreEvento;

    @FXML
    private TableColumn<InformacionEvento, String> columnDeporteEvento;

    @FXML
    private TableColumn<InformacionEvento, String> columnOlimpiadaEvento;

    @FXML
    private TableColumn<InformacionParticipacion, String> columnDeportistaParticipacion;

    @FXML
    private TableColumn<InformacionParticipacion, String> columnEquipoParticipacion;

    @FXML
    private TableColumn<InformacionParticipacion, String> columnEventoParticipacion;

    @FXML
    private TableColumn<InformacionParticipacion, Integer> columnEdadParticipacion;

    @FXML
    private TableColumn<InformacionParticipacion, String> columnMedallaParticipacion;

    private ResourceBundle bundle;

    private DeporteDao deporteDao;
    private DeportistaDao deportistaDao;
    private EquipoDao equipoDao;
    private OlimpiadaDao olimpiadaDao;
    private EventoDao eventoDao;
    private ParticipacionDao participacionDao;

    private ObservableList<Deporte> deportesExistentes;
    private ObservableList<Deportista> deportistasExistentes;
    private ObservableList<Equipo> equiposExistentes;
    private ObservableList<Olimpiada> olimpiadasExistentes;
    private ObservableList<InformacionEvento> eventosExistentes;
    private ObservableList<InformacionParticipacion> participacionesExistentes;

    private ObservableList<Deporte> deportesOriginales;
    private ObservableList<Deportista> deportistasOriginales;
    private ObservableList<Equipo> equiposOriginales;
    private ObservableList<Olimpiada> olimpiadasOriginales;
    private ObservableList<InformacionEvento> eventosOriginales;
    private ObservableList<InformacionParticipacion> participacionesOriginales;

    /** INITIALIZE - INICIALIZAR CARGA DE DATOS */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //Instanciar bundle para idiomas
        bundle = arg1;

        //Instanciar Daos
        deporteDao = new DeporteDao();
        deportistaDao = new DeportistaDao();
        equipoDao = new EquipoDao();
        olimpiadaDao = new OlimpiadaDao();
        eventoDao = new EventoDao();
        participacionDao = new ParticipacionDao();

        //Cargar Listas
        deportesExistentes = deporteDao.cargarDeportes();
        deportistasExistentes = deportistaDao.cargarDeportistas();
        equiposExistentes = equipoDao.cargarEquipos();
        olimpiadasExistentes = olimpiadaDao.cargarOlimpiadas();
        eventosExistentes = eventoDao.cargarEventos();
        participacionesExistentes = participacionDao.cargarParticipaciones();

        //Cargar columna Deporte
        columnNombreDeporte.setCellValueFactory(new PropertyValueFactory<Deporte, String>("Nombre"));

        //Cargar columna Deportista
        columnNombreDeportista.setCellValueFactory(new PropertyValueFactory<Deportista, String>("Nombre"));
        columnSexoDeportista.setCellValueFactory(new PropertyValueFactory<Deportista, String>("Sexo"));
        columnPesoDeportista.setCellValueFactory(new PropertyValueFactory<Deportista, Integer>("Peso"));
        columnAlturaDeportista.setCellValueFactory(new PropertyValueFactory<Deportista, Integer>("Altura"));

        //Cargar columna Equipo
        columnNombreEquipo.setCellValueFactory(new PropertyValueFactory<Equipo, String>("Nombre"));
        columnInicialesEquipo.setCellValueFactory(new PropertyValueFactory<Equipo, String>("Iniciales"));

        //Cargar columna Olimpiada
        columnNombreOlimpiada.setCellValueFactory(new PropertyValueFactory<Olimpiada, String>("Nombre"));
        columnAnioOlimpiada.setCellValueFactory(new PropertyValueFactory<Olimpiada, Integer>("Anio"));
        columnTemporadaOlimpiada.setCellValueFactory(new PropertyValueFactory<Olimpiada, String>("Temporada"));
        columnCiudadOlimpiada.setCellValueFactory(new PropertyValueFactory<Olimpiada, String>("Ciudad"));

        //Cargar columna Evento
        columnNombreEvento.setCellValueFactory(new PropertyValueFactory<InformacionEvento, String>("Nombre"));
        columnOlimpiadaEvento.setCellValueFactory(new PropertyValueFactory<InformacionEvento, String>("nombreOlimpiada"));
        columnDeporteEvento.setCellValueFactory(new PropertyValueFactory<InformacionEvento, String>("nombreDeporte"));

        //Cargar columna Participacion
        columnDeportistaParticipacion.setCellValueFactory(new PropertyValueFactory<InformacionParticipacion, String>("nombreDeportista"));
        columnEventoParticipacion.setCellValueFactory(new PropertyValueFactory<InformacionParticipacion, String>("nombreEvento"));
        columnEquipoParticipacion.setCellValueFactory(new PropertyValueFactory<InformacionParticipacion, String>("nombreEquipo"));
        columnEdadParticipacion.setCellValueFactory(new PropertyValueFactory<InformacionParticipacion, Integer>("Edad"));
        columnMedallaParticipacion.setCellValueFactory(new PropertyValueFactory<InformacionParticipacion, String>("Medalla"));

        //Cargar Tablas
        tableDeporte.setItems(deportesExistentes);
        tableDeportista.setItems(deportistasExistentes);
        tableEquipo.setItems(equiposExistentes);
        tableOlimpiada.setItems(olimpiadasExistentes);
        tableEvento.setItems(eventosExistentes);
        tableParticipacion.setItems(participacionesExistentes);

        //Filtro
        deportesOriginales = FXCollections.observableArrayList(deportesExistentes);
        deportistasOriginales = FXCollections.observableArrayList(deportistasExistentes);
        equiposOriginales = FXCollections.observableArrayList(equiposExistentes);
        olimpiadasOriginales = FXCollections.observableArrayList(olimpiadasExistentes);
        eventosOriginales = FXCollections.observableArrayList(eventosExistentes);
        participacionesOriginales = FXCollections.observableArrayList(participacionesExistentes);

        textFieldFiltro.textProperty().addListener((observable, oldValue, newValue) -> {String filter = newValue.toLowerCase();
            deportesExistentes.setAll(deportesOriginales.filtered(deporte -> deporte.getNombre().toLowerCase().contains(filter)));
            deportistasExistentes.setAll(deportistasOriginales.filtered(deportista -> deportista.getNombre().toLowerCase().contains(filter)));
            equiposExistentes.setAll(equiposOriginales.filtered(equipo -> equipo.getNombre().toLowerCase().contains(filter)));
            olimpiadasExistentes.setAll(olimpiadasOriginales.filtered(olimpiada -> olimpiada.getNombre().toLowerCase().contains(filter)));
            eventosExistentes.setAll(eventosOriginales.filtered(evento -> evento.getNombre().toLowerCase().contains(filter)));
            participacionesExistentes.setAll(participacionesOriginales.filtered(participacion -> String.valueOf(participacion.getEdad()).toLowerCase().contains(filter)));
        });

        //Menú contextual
        tableDeporte.setContextMenu(crearContextMenu("Deporte", this::actionAgregarDeporte, this::actionEditarDeporte, this::actionBorrarDeporte));
        tableDeportista.setContextMenu(crearContextMenu("Deportista", this::actionAgregarDeportista, this::actionEditarDeportista, this::actionBorrarDeportista));
        tableEquipo.setContextMenu(crearContextMenu("Equipo", this::actionAgregarEquipo, this::actionEditarEquipo, this::actionBorrarEquipo));
        tableOlimpiada.setContextMenu(crearContextMenu("Olimpiada", this::actionAgregarOlimpiada, this::actionEditarOlimpiada, this::actionBorrarOlimpiada));
        tableEvento.setContextMenu(crearContextMenu("Evento", this::actionAgregarEvento, this::actionEditarEvento, this::actionBorrarEvento));
        tableParticipacion.setContextMenu(crearContextMenu("Participacion", this::actionAgregarParticipacion, this::actionEditarParticipacion, this::actionBorrarParticipacion));

    }

    /** EVENTO - AL PULSAR DEPORTISTA DEL MENÚ */
    @FXML
    void actionBotonDeportista(ActionEvent event) {
        //Decoración del menú
        restaurarColorInicialPanel(panelDeportista);
        restaurarColorInicialBoton((Button)event.getSource());
        labelTablaActual.setText(bundle.getString("labelDeportistas"));

        //Mostrar tabla
        ocultarTablas();
        tableDeportista.setVisible(true);

        //Mostrar botones
        ocultarBotones();
        botonAgregarDeportista.setVisible(true);
        botonEditarDeportista.setVisible(true);
        botonBorrarDeportista.setVisible(true);
    }

    /** EVENTO - AL PULSAR OLIMPIADA DEL MENÚ */
    @FXML
    void actionBotonOlimpiada(ActionEvent event) {
        //Decoración del menú
        restaurarColorInicialPanel(panelOlimpiada);
        restaurarColorInicialBoton((Button)event.getSource());
        labelTablaActual.setText(bundle.getString("labelOlimpiadas"));

        //Mostrar tabla
        ocultarTablas();
        tableOlimpiada.setVisible(true);

        //Mostrar botones
        ocultarBotones();
        botonAgregarOlimpiada.setVisible(true);
        botonEditarOlimpiada.setVisible(true);
        botonBorrarOlimpiada.setVisible(true);
    }

    /** EVENTO - AL PULSAR EQUIPO DEL MENÚ */
    @FXML
    void actionBotonEquipo(ActionEvent event) {
        //Decoración del menú
        restaurarColorInicialPanel(panelEquipo);
        restaurarColorInicialBoton((Button)event.getSource());
        labelTablaActual.setText(bundle.getString("labelEquipos"));

        //Mostrar tabla
        ocultarTablas();
        tableEquipo.setVisible(true);

        //Mostrar botones
        ocultarBotones();
        botonAgregarEquipo.setVisible(true);
        botonEditarEquipo.setVisible(true);
        botonBorrarEquipo.setVisible(true);
    }

    /** EVENTO - AL PULSAR DEPORTE DEL MENÚ */
    @FXML
    void actionBotonDeporte(ActionEvent event) {
        //Decoración del menú
        restaurarColorInicialPanel(panelDeporte);
        restaurarColorInicialBoton((Button)event.getSource());
        labelTablaActual.setText(bundle.getString("labelDeportes"));

        //Mostrar tabla
        ocultarTablas();
        tableDeporte.setVisible(true);

        //Mostrar botones
        ocultarBotones();
        botonAgregarDeporte.setVisible(true);
        botonEditarDeporte.setVisible(true);
        botonBorrarDeporte.setVisible(true);
    }

    /** EVENTO - AL PULSAR EVENTO DEL MENÚ */
    @FXML
    void actionBotonEvento(ActionEvent event) {
        //Decoración del menú
        restaurarColorInicialPanel(panelEvento);
        restaurarColorInicialBoton((Button)event.getSource());
        labelTablaActual.setText(bundle.getString("labelEventos"));

        //Mostrar tabla
        ocultarTablas();
        tableEvento.setVisible(true);

        //Mostrar botones
        ocultarBotones();
        botonAgregarEvento.setVisible(true);
        botonEditarEvento.setVisible(true);
        botonBorrarEvento.setVisible(true);
    }

    /** EVENTO - AL PULSAR PARTICIPACION DEL MENÚ */
    @FXML
    void actionBotonParticipacion(ActionEvent event) {
        //Decoración del menú
        restaurarColorInicialPanel(panelParticipacion);
        restaurarColorInicialBoton((Button)event.getSource());
        labelTablaActual.setText(bundle.getString("labelParticipaciones"));

        //Mostrar tabla
        ocultarTablas();
        tableParticipacion.setVisible(true);

        //Mostrar botones
        ocultarBotones();
        botonAgregarParticipacion.setVisible(true);
        botonEditarParticipacion.setVisible(true);
        botonBorrarParticipacion.setVisible(true);
    }

    /** EVENTO - AL PULSAR AGREGAR DEPORTE*/
    @FXML
    void actionAgregarDeporte(ActionEvent event) {
        try {
            //Multilingue
            String idioma = Propiedades.getValor("idioma");
            String region = Propiedades.getValor("region");
            Locale.setDefault(new Locale(idioma, region));
            ResourceBundle bundle = ResourceBundle.getBundle("/eu/andreatt/proyecto1_dein/idiomas/messages");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/proyecto1_dein/fxml/AgregarDeporte.fxml"),bundle);
            Parent root = loader.load();

            //Creación del controlador
            AgregarDeporteController cargarControllerDeporte = loader.getController();
            //le pasamos el observableList para que al modificarlo, se actualice solo
            cargarControllerDeporte.initAttributtes(deportesExistentes);

            //Escena principal
            Scene scene = new Scene(root,390,152);

            Stage newStage = new Stage();
            scene.getStylesheets().add(getClass() .getResource("/eu/andreatt/proyecto1_dein/css/application.css").toExternalForm());
            newStage.setTitle(bundle.getString("labelAgregarDeporte"));
            newStage.setResizable(false);
            newStage.setScene(scene);

            //Modal
            newStage.initModality(Modality.APPLICATION_MODAL);

            //Logo
            Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/proyecto1_dein/images/deporte.png"));
            newStage.getIcons().add(icon);

            newStage.showAndWait();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /** EVENTO - AL PULSAR AGREGAR DEPORTISTA*/
    @FXML
    void actionAgregarDeportista(ActionEvent event) {
        try {
            //Multilingue
            String idioma = Propiedades.getValor("idioma");
            String region = Propiedades.getValor("region");
            Locale.setDefault(new Locale(idioma, region));
            ResourceBundle bundle = ResourceBundle.getBundle("/eu/andreatt/proyecto1_dein/idiomas/messages");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/proyecto1_dein/fxml/AgregarDeportista.fxml"),bundle);
            Parent root = loader.load();

            //Creación del controlador
            AgregarDeportistaController cargarControllerDeportista = loader.getController();
            //le pasamos el observableList para que al modificarlo, se actualice solo
            cargarControllerDeportista.initAttributtes(deportistasExistentes);

            //Escena principal
            Scene scene = new Scene(root,395,283);

            Stage newStage = new Stage();
            scene.getStylesheets().add(getClass() .getResource("/eu/andreatt/proyecto1_dein/css/application.css").toExternalForm());
            newStage.setTitle(bundle.getString("labelAgregarDeportista"));
            newStage.setResizable(false);
            newStage.setScene(scene);

            //Modal
            newStage.initModality(Modality.APPLICATION_MODAL);

            //Logo
            Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/proyecto1_dein/images/deportista.png"));
            newStage.getIcons().add(icon);

            newStage.showAndWait();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /** EVENTO - AL PULSAR AGREGAR EQUIPO*/
    @FXML
    void actionAgregarEquipo(ActionEvent event) {
        try {
            //Multilingue
            String idioma = Propiedades.getValor("idioma");
            String region = Propiedades.getValor("region");
            Locale.setDefault(new Locale(idioma, region));
            ResourceBundle bundle = ResourceBundle.getBundle("/eu/andreatt/proyecto1_dein/idiomas/messages");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/proyecto1_dein/fxml/AgregarEquipo.fxml"),bundle);
            Parent root = loader.load();

            //Creación del controlador
            AgregarEquipoController cargarControllerEquipo = loader.getController();
            //le pasamos el observableList para que al modificarlo, se actualice solo
            cargarControllerEquipo.initAttributtes(equiposExistentes);

            //Escena principal
            Scene scene = new Scene(root,397,211);

            Stage newStage = new Stage();
            scene.getStylesheets().add(getClass() .getResource("/eu/andreatt/proyecto1_dein/css/application.css").toExternalForm());
            newStage.setTitle(bundle.getString("labelAgregarEquipo"));
            newStage.setResizable(false);
            newStage.setScene(scene);

            //Modal
            newStage.initModality(Modality.APPLICATION_MODAL);

            //Logo
            Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/proyecto1_dein/images/equipo.png"));
            newStage.getIcons().add(icon);

            newStage.showAndWait();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /** EVENTO - AL PULSAR AGREGAR EVENTO*/
    @FXML
    void actionAgregarEvento(ActionEvent event) {
        try {
            //Multilingue
            String idioma = Propiedades.getValor("idioma");
            String region = Propiedades.getValor("region");
            Locale.setDefault(new Locale(idioma, region));
            ResourceBundle bundle = ResourceBundle.getBundle("/eu/andreatt/proyecto1_dein/idiomas/messages");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/proyecto1_dein/fxml/AgregarEvento.fxml"),bundle);
            Parent root = loader.load();

            //Creación del controlador
            AgregarEventoController cargarControllerEvento = loader.getController();
            //le pasamos el observableList para que al modificarlo, se actualice solo
            cargarControllerEvento.initAttributtes(eventosExistentes);

            //Escena principal
            Scene scene = new Scene(root,397,238);

            Stage newStage = new Stage();
            scene.getStylesheets().add(getClass() .getResource("/eu/andreatt/proyecto1_dein/css/application.css").toExternalForm());
            newStage.setTitle(bundle.getString("labelAgregarEvento"));
            newStage.setResizable(false);
            newStage.setScene(scene);

            //Modal
            newStage.initModality(Modality.APPLICATION_MODAL);

            //Logo
            Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/proyecto1_dein/images/evento.png"));
            newStage.getIcons().add(icon);

            newStage.showAndWait();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /** EVENTO - AL PULSAR AGREGAR OLIMPIADA*/
    @FXML
    void actionAgregarOlimpiada(ActionEvent event) {
        try {
            //Multilingue
            String idioma = Propiedades.getValor("idioma");
            String region = Propiedades.getValor("region");
            Locale.setDefault(new Locale(idioma, region));
            ResourceBundle bundle = ResourceBundle.getBundle("/eu/andreatt/proyecto1_dein/idiomas/messages");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/proyecto1_dein/fxml/AgregarOlimpiada.fxml"),bundle);
            Parent root = loader.load();

            //Creación del controlador
            AgregarOlimpiadaController cargarControllerOlimpiada = loader.getController();
            //le pasamos el observableList para que al modificarlo, se actualice solo
            cargarControllerOlimpiada.initAttributtes(olimpiadasExistentes);

            //Escena principal
            Scene scene = new Scene(root,395,283);

            Stage newStage = new Stage();
            scene.getStylesheets().add(getClass() .getResource("/eu/andreatt/proyecto1_dein/css/application.css").toExternalForm());
            newStage.setTitle(bundle.getString("labelAgregarOlimpiada"));
            newStage.setResizable(false);
            newStage.setScene(scene);

            //Modal
            newStage.initModality(Modality.APPLICATION_MODAL);

            //Logo
            Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/proyecto1_dein/images/olimpiada.png"));
            newStage.getIcons().add(icon);

            newStage.showAndWait();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /** EVENTO - AL PULSAR AGREGAR PARTICIPACION*/
    @FXML
    void actionAgregarParticipacion(ActionEvent event) {
        try {
            //Multilingue
            String idioma = Propiedades.getValor("idioma");
            String region = Propiedades.getValor("region");
            Locale.setDefault(new Locale(idioma, region));
            ResourceBundle bundle = ResourceBundle.getBundle("/eu/andreatt/proyecto1_dein/idiomas/messages");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/proyecto1_dein/fxml/AgregarParticipacion.fxml"),bundle);
            Parent root = loader.load();

            //Creación del controlador
            AgregarParticipacionController cargarControllerParticipacion = loader.getController();
            //le pasamos el observableList para que al modificarlo, se actualice solo
            cargarControllerParticipacion.initAttributtes(participacionesExistentes);

            //Escena principal
            Scene scene = new Scene(root,396,310);

            Stage newStage = new Stage();
            scene.getStylesheets().add(getClass() .getResource("/eu/andreatt/proyecto1_dein/css/application.css").toExternalForm());
            newStage.setTitle(bundle.getString("labelAgregarParticipacion"));
            newStage.setResizable(false);
            newStage.setScene(scene);

            //Modal
            newStage.initModality(Modality.APPLICATION_MODAL);

            //Logo
            Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/proyecto1_dein/images/participacion.png"));
            newStage.getIcons().add(icon);

            newStage.showAndWait();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /** EVENTO - AL PULSAR BORRAR DEPORTE*/
    @FXML
    void actionBorrarDeporte(ActionEvent event) {
        //Deporte Seleccionado
        Deporte itemSeleccionado = tableDeporte.getSelectionModel().getSelectedItem();

        if(itemSeleccionado!=null) {
            //Borrar participación
            int idEvento = eventoDao.dameIdDeEventoPorDeporte(itemSeleccionado.getId_deporte());
            participacionDao.borrarParticipacionPorEvento(idEvento);

            //Borrar evento
            eventoDao.borrarEvento(idEvento);

            //Borrar deporte
            deporteDao.borrarDeporte(itemSeleccionado.getId_deporte());

            //Actualizar datos en las tablas
            tableDeporte.setItems(deporteDao.cargarDeportes());
            tableEvento.setItems(eventoDao.cargarEventos());
            tableParticipacion.setItems(participacionDao.cargarParticipaciones());
            tableDeporte.refresh();
            tableEvento.refresh();
            tableParticipacion.refresh();

            //Mostrar alerta
            generarVentana(AlertType.INFORMATION, bundle.getString("borradoDeporteCorrecto"), "INFO");
        }else {
            generarVentana(AlertType.ERROR, bundle.getString("borradoDeporteIncorrecto"), "ERROR");
        }
    }

    /** EVENTO - AL PULSAR BORRAR DEPORTISTA*/
    @FXML
    void actionBorrarDeportista(ActionEvent event) {
        //Deportista Seleccionado
        Deportista itemSeleccionado = tableDeportista.getSelectionModel().getSelectedItem();

        if(itemSeleccionado!=null) {
            //Borrar participación
            participacionDao.borrarParticipacionPorDeportista(itemSeleccionado.getId_deportista());

            //Borrar Deportista
            deportistaDao.borrarDeportista(itemSeleccionado.getId_deportista());

            //Actualizar datos en las tablas
            tableParticipacion.setItems(participacionDao.cargarParticipaciones());
            tableDeportista.setItems(deportistaDao.cargarDeportistas());
            tableParticipacion.refresh();
            tableDeportista.refresh();

            //Mostrar alerta
            generarVentana(AlertType.INFORMATION, bundle.getString("borradoDeportistaCorrecto"), "INFO");
        }else {
            generarVentana(AlertType.ERROR, bundle.getString("borradoDeportistaIncorrecto"), "ERROR");
        }
    }

    /** EVENTO - AL PULSAR BORRAR EQUIPO*/
    @FXML
    void actionBorrarEquipo(ActionEvent event) {
        //Equipo Seleccionado
        Equipo itemSeleccionado = tableEquipo.getSelectionModel().getSelectedItem();

        if(itemSeleccionado!=null) {
            //Borrar participación
            participacionDao.borrarParticipacionPorDeportista(itemSeleccionado.getId_equipo());

            //Borrar Equipo
            equipoDao.borrarEquipo(itemSeleccionado.getId_equipo());

            //Actualizar datos en las tablas
            tableParticipacion.setItems(participacionDao.cargarParticipaciones());
            tableEquipo.setItems(equipoDao.cargarEquipos());
            tableParticipacion.refresh();
            tableEquipo.refresh();

            //Mostrar alerta
            generarVentana(AlertType.INFORMATION, bundle.getString("borradoEquipoCorrecto"), "INFO");
        }else {
            generarVentana(AlertType.ERROR, bundle.getString("borradoEquipoIncorrecto"), "ERROR");
        }
    }

    /** EVENTO - AL PULSAR BORRAR EVENTO*/
    @FXML
    void actionBorrarEvento(ActionEvent event) {
        //Evento Seleccionado
        InformacionEvento itemSeleccionado = tableEvento.getSelectionModel().getSelectedItem();

        if(itemSeleccionado!=null) {
            //Borrar participación
            int id_evento = eventoDao.dameIdDeEvento(itemSeleccionado.getNombre());
            participacionDao.borrarParticipacionPorDeportista(id_evento);

            //Borrar Evento
            eventoDao.borrarEvento(id_evento);

            //Actualizar datos en las tablas
            tableParticipacion.setItems(participacionDao.cargarParticipaciones());
            tableEvento.setItems(eventoDao.cargarEventos());
            tableParticipacion.refresh();
            tableEvento.refresh();

            //Mostrar alerta
            generarVentana(AlertType.INFORMATION, bundle.getString("borradoEventoCorrecto"), "INFO");
        }else {
            generarVentana(AlertType.ERROR, bundle.getString("borradoEventoIncorrecto"), "ERROR");
        }
    }

    /** EVENTO - AL PULSAR BORRAR OLIMPIADA*/
    @FXML
    void actionBorrarOlimpiada(ActionEvent event) {
        //Olimpiada Seleccionada
        Olimpiada itemSeleccionado = tableOlimpiada.getSelectionModel().getSelectedItem();

        if(itemSeleccionado!=null) {
            //Borrar evento
            eventoDao.borrarEventoPorOlimpiada(itemSeleccionado.getId_olimpiada());

            //Borrar Olimpiada
            olimpiadaDao.borrarOlimpiada(itemSeleccionado.getId_olimpiada());

            //Actualizar datos en las tablas
            tableEvento.setItems(eventoDao.cargarEventos());
            tableOlimpiada.setItems(olimpiadaDao.cargarOlimpiadas());
            tableEvento.refresh();
            tableOlimpiada.refresh();

            //Mostrar alerta
            generarVentana(AlertType.INFORMATION, bundle.getString("borradoOlimpiadaCorrecto"), "INFO");
        }else {
            generarVentana(AlertType.ERROR, bundle.getString("borradOlimpiadaIncorrecto"), "ERROR");
        }
    }

    /** EVENTO - AL PULSAR BORRAR PARTICIPACIÓN*/
    @FXML
    void actionBorrarParticipacion(ActionEvent event) {
        //Participación Seleccionada
        InformacionParticipacion itemSeleccionado = tableParticipacion.getSelectionModel().getSelectedItem();

        if(itemSeleccionado!=null) {
            //Borrar participación
            int id_deportista = deportistaDao.dameIdDeDeportista(itemSeleccionado.getNombreDeportista());
            int id_evento = eventoDao.dameIdDeEvento(itemSeleccionado.getNombreEvento());
            int id_equipo = equipoDao.dameIdDeEquipo(itemSeleccionado.getNombreEquipo());
            participacionDao.borrarParticipacion(id_deportista, id_evento, id_equipo, itemSeleccionado.getEdad());

            //Actualizar datos en las tablas
            tableParticipacion.setItems(participacionDao.cargarParticipaciones());
            tableParticipacion.refresh();

            //Mostrar alerta
            generarVentana(AlertType.INFORMATION, bundle.getString("borradoParticipacionCorrecto"), "INFO");
        }else {
            generarVentana(AlertType.ERROR, bundle.getString("borradoParticipacionIncorrecto"), "ERROR");
        }
    }

    /** EVENTO - AL PULSAR EDITAR DEPORTE*/
    @FXML
    void actionEditarDeporte(ActionEvent event) {
        //Deporte Seleccionado
        Deporte itemSeleccionado = tableDeporte.getSelectionModel().getSelectedItem();

        if(itemSeleccionado!=null) {
            try {
                //Multilingue
                String idioma = Propiedades.getValor("idioma");
                String region = Propiedades.getValor("region");
                Locale.setDefault(new Locale(idioma, region));
                ResourceBundle bundle = ResourceBundle.getBundle("/eu/andreatt/proyecto1_dein/idiomas/messages");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/proyecto1_dein/fxml/AgregarDeporte.fxml"),bundle);
                Parent root = loader.load();

                //Creación del controlador
                AgregarDeporteController cargarControllerDeporte = loader.getController();

                //Enviar datos necesarios para que se actualice solo
                Deporte deporte = tableDeporte.getSelectionModel().getSelectedItem();
                cargarControllerDeporte.initAttributtes(deportesExistentes, deporte);

                //Escena principal
                Scene scene = new Scene(root,390,152);

                Stage newStage = new Stage();
                scene.getStylesheets().add(getClass() .getResource("/eu/andreatt/proyecto1_dein/css/application.css").toExternalForm());
                newStage.setTitle(bundle.getString("labelEditarDeporte"));
                newStage.setResizable(false);
                newStage.setScene(scene);

                //Modal
                newStage.initModality(Modality.APPLICATION_MODAL);

                //Logo
                Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/proyecto1_dein/images/deporte.png"));
                newStage.getIcons().add(icon);

                newStage.showAndWait();

            } catch(Exception e) {
                e.printStackTrace();
            }
        }else {
            generarVentana(AlertType.ERROR, bundle.getString("borradoDeporteIncorrecto"), "ERROR");
        }
    }

    /** EVENTO - AL PULSAR EDITAR DEPORTISTA*/
    @FXML
    void actionEditarDeportista(ActionEvent event) {
        //Deportista Seleccionado
        Deportista itemSeleccionado = tableDeportista.getSelectionModel().getSelectedItem();

        if(itemSeleccionado!=null) {
            try {
                //Multilingue
                String idioma = Propiedades.getValor("idioma");
                String region = Propiedades.getValor("region");
                Locale.setDefault(new Locale(idioma, region));
                ResourceBundle bundle = ResourceBundle.getBundle("/eu/andreatt/proyecto1_dein/idiomas/messages");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/proyecto1_dein/fxml/AgregarDeportista.fxml"),bundle);
                Parent root = loader.load();

                //Creación del controlador
                AgregarDeportistaController cargarControllerDeportista = loader.getController();

                //Enviar datos necesarios para que se actualice solo
                Deportista deportista = tableDeportista.getSelectionModel().getSelectedItem();
                cargarControllerDeportista.initAttributtes(deportistasExistentes, deportista);

                //Escena principal
                Scene scene = new Scene(root,395,283);

                Stage newStage = new Stage();
                scene.getStylesheets().add(getClass() .getResource("/eu/andreatt/proyecto1_dein/css/application.css").toExternalForm());
                newStage.setTitle(bundle.getString("labelEditarDeportista"));
                newStage.setResizable(false);
                newStage.setScene(scene);

                //Modal
                newStage.initModality(Modality.APPLICATION_MODAL);

                //Logo
                Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/proyecto1_dein/images/deportista.png"));
                newStage.getIcons().add(icon);

                newStage.showAndWait();

            } catch(Exception e) {
                e.printStackTrace();
            }
        }else {
            generarVentana(AlertType.ERROR, bundle.getString("borradoDeportistaIncorrecto"), "ERROR");
        }
    }

    /** EVENTO - AL PULSAR EDITAR EQUIPO*/
    @FXML
    void actionEditarEquipo(ActionEvent event) {
        //Equipo Seleccionado
        Equipo itemSeleccionado = tableEquipo.getSelectionModel().getSelectedItem();

        if(itemSeleccionado!=null) {
            try {
                //Multilingue
                String idioma = Propiedades.getValor("idioma");
                String region = Propiedades.getValor("region");
                Locale.setDefault(new Locale(idioma, region));
                ResourceBundle bundle = ResourceBundle.getBundle("/eu/andreatt/proyecto1_dein/idiomas/messages");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/proyecto1_dein/fxml/AgregarEquipo.fxml"),bundle);
                Parent root = loader.load();

                //Creación del controlador
                AgregarEquipoController cargarControllerEquipo = loader.getController();

                //Enviar datos necesarios para que se actualice solo
                Equipo equipo = tableEquipo.getSelectionModel().getSelectedItem();
                cargarControllerEquipo.initAttributtes(equiposExistentes, equipo);

                //Escena principal
                Scene scene = new Scene(root,397,211);

                Stage newStage = new Stage();
                scene.getStylesheets().add(getClass() .getResource("/eu/andreatt/proyecto1_dein/css/application.css").toExternalForm());
                newStage.setTitle(bundle.getString("labelEditarEquipo"));
                newStage.setResizable(false);
                newStage.setScene(scene);

                //Modal
                newStage.initModality(Modality.APPLICATION_MODAL);

                //Logo
                Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/proyecto1_dein/images/equipo.png"));
                newStage.getIcons().add(icon);

                newStage.showAndWait();

            } catch(Exception e) {
                e.printStackTrace();
            }
        }else {
            generarVentana(AlertType.ERROR, bundle.getString("borradoEquipoIncorrecto"), "ERROR");
        }
    }

    /** EVENTO - AL PULSAR EDITAR EVENTO*/
    @FXML
    void actionEditarEvento(ActionEvent event) {
        //Evento Seleccionado
        InformacionEvento itemSeleccionado = tableEvento.getSelectionModel().getSelectedItem();

        if(itemSeleccionado!=null) {
            try {
                //Multilingue
                String idioma = Propiedades.getValor("idioma");
                String region = Propiedades.getValor("region");
                Locale.setDefault(new Locale(idioma, region));
                ResourceBundle bundle = ResourceBundle.getBundle("/eu/andreatt/proyecto1_dein/idiomas/messages");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/proyecto1_dein/fxml/AgregarEvento.fxml"),bundle);
                Parent root = loader.load();

                //Creación del controlador
                AgregarEventoController cargarControllerEvento = loader.getController();

                //Enviar datos necesarios para que se actualice solo
                InformacionEvento evento = tableEvento.getSelectionModel().getSelectedItem();
                cargarControllerEvento.initAttributtes(eventosExistentes, evento);

                //Escena principal
                Scene scene = new Scene(root,397,238);

                Stage newStage = new Stage();
                scene.getStylesheets().add(getClass() .getResource("/eu/andreatt/proyecto1_dein/css/application.css").toExternalForm());
                newStage.setTitle(bundle.getString("labelEditarEvento"));
                newStage.setResizable(false);
                newStage.setScene(scene);

                //Modal
                newStage.initModality(Modality.APPLICATION_MODAL);

                //Logo
                Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/proyecto1_dein/images/evento.png"));
                newStage.getIcons().add(icon);

                newStage.showAndWait();

            } catch(Exception e) {
                e.printStackTrace();
            }
        }else {
            generarVentana(AlertType.ERROR, bundle.getString("borradoEventoIncorrecto"), "ERROR");
        }
    }

    /** EVENTO - AL PULSAR EDITAR OLIMPIADA*/
    @FXML
    void actionEditarOlimpiada(ActionEvent event) {
        //Olimpiada Seleccionada
        Olimpiada itemSeleccionado = tableOlimpiada.getSelectionModel().getSelectedItem();

        if(itemSeleccionado!=null) {
            try {
                //Multilingue
                String idioma = Propiedades.getValor("idioma");
                String region = Propiedades.getValor("region");
                Locale.setDefault(new Locale(idioma, region));
                ResourceBundle bundle = ResourceBundle.getBundle("/eu/andreatt/proyecto1_dein/idiomas/messages");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/proyecto1_dein/fxml/AgregarOlimpiada.fxml"),bundle);
                Parent root = loader.load();

                //Creación del controlador
                AgregarOlimpiadaController cargarControllerOlimpiada = loader.getController();

                //Enviar datos necesarios para que se actualice solo
                Olimpiada olimpiada = tableOlimpiada.getSelectionModel().getSelectedItem();
                cargarControllerOlimpiada.initAttributtes(olimpiadasExistentes, olimpiada);

                //Escena principal
                Scene scene = new Scene(root,395,283);

                Stage newStage = new Stage();
                scene.getStylesheets().add(getClass() .getResource("/eu/andreatt/proyecto1_dein/css/application.css").toExternalForm());
                newStage.setTitle(bundle.getString("labelEditarOlimpiada"));
                newStage.setResizable(false);
                newStage.setScene(scene);

                //Modal
                newStage.initModality(Modality.APPLICATION_MODAL);

                //Logo
                Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/proyecto1_dein/images/olimpiada.png"));
                newStage.getIcons().add(icon);

                newStage.showAndWait();

            } catch(Exception e) {
                e.printStackTrace();
            }
        }else {
            generarVentana(AlertType.ERROR, bundle.getString("borradoOlimpiadaIncorrecto"), "ERROR");
        }
    }

    /** EVENTO - AL PULSAR EDITAR PARTICIPACIÓN*/
    @FXML
    void actionEditarParticipacion(ActionEvent event) {
        //Participación Seleccionada
        InformacionParticipacion itemSeleccionado = tableParticipacion.getSelectionModel().getSelectedItem();

        if(itemSeleccionado!=null) {
            try {
                //Multilingue
                String idioma = Propiedades.getValor("idioma");
                String region = Propiedades.getValor("region");
                Locale.setDefault(new Locale(idioma, region));
                ResourceBundle bundle = ResourceBundle.getBundle("/eu/andreatt/proyecto1_dein/idiomas/messages");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/proyecto1_dein/fxml/AgregarParticipacion.fxml"),bundle);
                Parent root = loader.load();

                //Creación del controlador
                AgregarParticipacionController cargarControllerParticipacion = loader.getController();

                //Enviar datos necesarios para que se actualice solo
                InformacionParticipacion participacion = tableParticipacion.getSelectionModel().getSelectedItem();
                cargarControllerParticipacion.initAttributtes(participacionesExistentes, participacion);

                //Escena principal
                Scene scene = new Scene(root,396,310);

                Stage newStage = new Stage();
                scene.getStylesheets().add(getClass() .getResource("/eu/andreatt/proyecto1_dein/css/application.css").toExternalForm());
                newStage.setTitle(bundle.getString("labelEditarParticipacion"));
                newStage.setResizable(false);
                newStage.setScene(scene);

                //Modal
                newStage.initModality(Modality.APPLICATION_MODAL);

                //Logo
                Image icon = new Image(getClass().getResourceAsStream("/eu/andreatt/proyecto1_dein/images/participacion.png"));
                newStage.getIcons().add(icon);

                newStage.showAndWait();

            } catch(Exception e) {
                e.printStackTrace();
            }
        }else {
            generarVentana(AlertType.ERROR, bundle.getString("borradoParticipacionIncorrecto"), "ERROR");
        }
    }

    /** CREAR MENÚ CONTEXTUAL */
    private ContextMenu crearContextMenu(String entidad, EventHandler<ActionEvent> agregarAction, EventHandler<ActionEvent> editarAction, EventHandler<ActionEvent> borrarAction) {
        String textoAgregar = "botonAgregar"+entidad;
        String textoEditar = "botonEditar"+entidad;
        String textoBorrar = "botonBorrar"+entidad;

        ContextMenu contextMenu = new ContextMenu();

        MenuItem agregarItem = new MenuItem(bundle.getString(textoAgregar));
        MenuItem modificarItem = new MenuItem(bundle.getString(textoEditar));
        MenuItem eliminarItem = new MenuItem(bundle.getString(textoBorrar));

        agregarItem.setOnAction(agregarAction);
        modificarItem.setOnAction(editarAction);
        eliminarItem.setOnAction(borrarAction);

        contextMenu.getItems().addAll(agregarItem, modificarItem, eliminarItem);

        return contextMenu;
    }

    /** CAMBIAR COLOR DEL PANEL SELECCIONADO */
    private void restaurarColorInicialPanel(GridPane panelExcepcion) {
        panelDeporte.setStyle("-fx-background-color: white;");
        panelDeportista.setStyle("-fx-background-color: white;");
        panelEquipo.setStyle("-fx-background-color: white;");
        panelEvento.setStyle("-fx-background-color: white;");
        panelOlimpiada.setStyle("-fx-background-color: white;");
        panelParticipacion.setStyle("-fx-background-color: white;");
        panelExcepcion.setStyle("-fx-background-color: lightgrey;");
    }

    /** CAMBIAR COLOR DEL BOTON SELECCIONADO */
    private void restaurarColorInicialBoton(Button botonExcepcion) {
        botonDeporte.setStyle("-fx-background-color: lightgrey;");
        botonDeportista.setStyle("-fx-background-color: lightgrey;");
        botonEquipo.setStyle("-fx-background-color: lightgrey;");
        botonEvento.setStyle("-fx-background-color: lightgrey;");
        botonOlimpiada.setStyle("-fx-background-color: lightgrey;");
        botonParticipacion.setStyle("-fx-background-color: lightgrey;");
        botonExcepcion.setStyle("-fx-background-color: white;");
    }

    /** OCULTAR TABLAS EN EL PANEL PRINCIPAL */
    private void ocultarTablas() {
        tableDeporte.setVisible(false);
        tableDeportista.setVisible(false);
        tableEquipo.setVisible(false);
        tableOlimpiada.setVisible(false);
        tableEvento.setVisible(false);
        tableParticipacion.setVisible(false);

        textFieldFiltro.setVisible(true);
    }

    /** OCULTAR BOTONES EN EL PANEL PRINCIPAL */
    private void ocultarBotones() {
        botonAgregarDeporte.setVisible(false);
        botonAgregarDeportista.setVisible(false);
        botonAgregarEquipo.setVisible(false);
        botonAgregarEvento.setVisible(false);
        botonAgregarOlimpiada.setVisible(false);
        botonAgregarParticipacion.setVisible(false);
        botonEditarDeporte.setVisible(false);
        botonEditarDeportista.setVisible(false);
        botonEditarEquipo.setVisible(false);
        botonEditarEvento.setVisible(false);
        botonEditarOlimpiada.setVisible(false);
        botonEditarParticipacion.setVisible(false);
        botonBorrarDeporte.setVisible(false);
        botonBorrarDeportista.setVisible(false);
        botonBorrarEquipo.setVisible(false);
        botonBorrarEvento.setVisible(false);
        botonBorrarOlimpiada.setVisible(false);
        botonBorrarParticipacion.setVisible(false);
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