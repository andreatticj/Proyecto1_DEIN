module eu.andreatt.proyecto1_dein {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.sql;


    opens eu.andreatt.proyecto1_dein.application to javafx.graphics, javafx.fxml;
    opens eu.andreatt.proyecto1_dein.controllers to javafx.graphics, javafx.fxml;
    opens eu.andreatt.proyecto1_dein.model to javafx.base;
}