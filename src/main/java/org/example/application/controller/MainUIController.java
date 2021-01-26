package org.example.application.controller;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import org.example.application.view.WindowLoader;

import java.io.IOException;

public class MainUIController {
    public void findPerson(ActionEvent actionEvent) {

    }

    public void getSelectedAndSetButton(MouseEvent mouseEvent) {

    }

    public void managePerson(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PersonUI");

    }
}
