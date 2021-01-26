module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;

    opens org.example.application.view to javafx.fxml;
    opens org.example.application.controller to javafx.fxml;
    opens org.example.domain.entities.person to javafx.fxml;

    exports org.example.application.controller;
    exports org.example.application.view;
    exports org.example.domain.entities.person;
}