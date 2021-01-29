package org.example.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.application.view.WindowLoader;
import org.example.domain.entities.importation.ImportedProduct;
import org.example.domain.entities.importation.ProductCategory;
import org.example.domain.entities.person.Person;

import java.io.IOException;
import java.util.Optional;

import static org.example.application.main.Main.findPersonUseCase;
import static org.example.application.main.Main.importProductUseCase;

public class ImportationUIController {
    @FXML
    public Label lbPersonId;
    @FXML
    public Label lbName;
    @FXML
    public Label lbPrice;
    @FXML
    public Label lbValidatedPerson;
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

    private Person person;
    private ImportedProduct importedProduct;

    @FXML
    private void initialize() {
        disableImportationFieldsAndButton();
        cbxCategory.getItems().setAll(ProductCategory.values());
    }

    public void importProduct(ActionEvent actionEvent) {
        if (importedProduct == null) {
            createImportedProduct();
        }

        getEntityFromView();

        importProductUseCase.importProduct(importedProduct);
    }

    private void createImportedProduct() {
        importedProduct = new ImportedProduct(
                cbxCategory.getValue(),
                txtName.getText(),
                Double.valueOf(txtPrice.getText()),
                Double.valueOf(txtWeight.getText()),
                Long.valueOf(txtPersonID.getText())
        );
    }

    private void getEntityFromView() {
        importedProduct.setProductCategory(cbxCategory.getValue());
        importedProduct.setProductName(txtName.getText());
        importedProduct.setProductPrice(Double.valueOf(txtPrice.getText()));
        importedProduct.setProductWeightInKG(Double.valueOf(txtWeight.getText()));
    }


    public void validatePerson(ActionEvent actionEvent) {
        long personId;

        try {
            personId = Long.parseLong(txtPersonID.getText());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Person ID must be a valid number.");
        }

        Optional<Person> result = findPersonUseCase.findOne(personId);
        if (result.isPresent()) {
            person = result.get();
            lbValidatedPerson.setText(person.getFirstName() + " " + person.getLastName());
            enableImportationFieldsAndButton();
        } else {
            person = null;
            lbValidatedPerson.setText("");
            showAlert("Erro!", "Pessoa não encontrada", Alert.AlertType.ERROR);
        }
    }


    private void enableImportationFieldsAndButton() {
        txtName.setDisable(false);
        txtPrice.setDisable(false);
        txtWeight.setDisable(false);
        btnImport.setDisable(false);
        cbxCategory.setDisable(false);
    }

    private void disableImportationFieldsAndButton() {
        txtName.setDisable(true);
        txtPrice.setDisable(true);
        txtWeight.setDisable(true);
        btnImport.setDisable(true);
        cbxCategory.setDisable(true);
    }


    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }
}
