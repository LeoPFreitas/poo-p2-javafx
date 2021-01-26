package org.example.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.domain.entities.person.Person;
import org.example.domain.entities.person.PersonType;

public class PersonUIController {
    @FXML
    private Label lbFirstName;
    @FXML
    private Label lbEmail;
    @FXML
    private Label lbRg;
    @FXML
    private Label lbLastName;
    @FXML
    private Label lbPersonType;
    @FXML
    private Label lbCnpj;
    @FXML
    private Label lbCpf;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtRg;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtCnpj;
    @FXML
    private TextField txtLastName;
    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnCancel;
    @FXML
    private ComboBox<PersonType> cbxPersonType;

    public void setUser(Person selectedPerson, UIMode mode) {

    }

    public void saveOrUpdate(ActionEvent actionEvent) {

    }

    public void backToPreviousScene(ActionEvent actionEvent) {

    }
}
