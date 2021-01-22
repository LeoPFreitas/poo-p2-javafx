module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.application.view to javafx.fxml;
    exports org.example.application.view;
}