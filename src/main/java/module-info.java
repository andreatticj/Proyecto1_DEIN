module eu.andreatt.proyecto1_dein {
    requires javafx.controls;
    requires javafx.fxml;


    opens eu.andreatt.proyecto1_dein to javafx.fxml;
    exports eu.andreatt.proyecto1_dein;
}