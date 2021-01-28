package org.example.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.domain.entities.importation.ProductCategory;

public class ImportationUIController {
    @FXML
    public Label lbPersonId;
    @FXML
    public Label lbName;
    @FXML
    public Label lbPrice;
    @FXML
    public Label lbLastName;
    @FXML
    public Label lbCategory;
    @FXML
    public TextField txtPersonID;
    @FXML
    public TextField txtName;
    @FXML
    public TextField txtPrice;
    @FXML
    public TextField txtWeight;
    @FXML
    public Button btnImport;
    @FXML
    public Button btnValidatePerson;
    @FXML
    public Button btnCancel;
    @FXML
    public ComboBox<ProductCategory> cbxCategory;
    @FXML
    public Label lbWeight;

    public void importProduct(ActionEvent actionEvent) {

    }

    public void validatePerson(ActionEvent actionEvent) {

    }

    public void backToPreviousScene(ActionEvent actionEvent) {

    }
}
