package org.example.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.application.view.WindowLoader;
import org.example.domain.entities.person.Person;
import org.example.domain.entities.person.PersonType;
import org.example.domain.entities.person.PessoaFisica;
import org.example.domain.entities.person.PessoaJuridica;
import org.example.domain.utils.IllegalPersonTypeException;

import java.io.IOException;

import static org.example.application.main.Main.createPersonUseCase;
import static org.example.application.main.Main.updatePersonUseCase;

public class PersonUIController {
    @FXML
    public Button btnUpdate;
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

    private Person person;

    @FXML
    private void initialize() {
        cbxPersonType.getItems().setAll(PersonType.values());
    }

    public void showInProperMode(UIMode uiMode) {
        switchUIMode(uiMode);
    }

    public void showInProperMode(UIMode uiMode, Person selectedPerson) {
        if (selectedPerson == null) {
            throw new IllegalArgumentException("Person can not be null.");
        }

        this.person = selectedPerson;
        setEntityIntoView();
        switchUIMode(uiMode);
    }

    private void switchUIMode(UIMode uiMode) {
        switch (uiMode) {
            case VIEW:
                configureViewUIMode();
                break;
            case CREATE:
                configureCreateUIMode();
                break;
            case UPDATE:
                configureUpdateUIMode();
                break;
        }
    }

    private void configureCreateUIMode() {
        btnConfirm.setVisible(true);
        btnUpdate.setVisible(false);
    }

    private void configureUpdateUIMode() {
        btnConfirm.setVisible(false);
        btnUpdate.setVisible(true);
    }

    private void configureViewUIMode() {
        btnCancel.setLayoutX(btnConfirm.getLayoutX());
        btnCancel.setLayoutY(btnConfirm.getLayoutY());
        btnCancel.setText("Fechar");

        btnConfirm.setVisible(false);
        btnUpdate.setVisible(false);

        txtFirstName.setDisable(true);
        txtEmail.setDisable(true);
        txtRg.setDisable(true);
        txtCpf.setDisable(true);
        txtCnpj.setDisable(true);
        txtLastName.setDisable(true);
        cbxPersonType.setDisable(true);
    }

    private void setEntityIntoView() {
        txtFirstName.setText(person.getFirstName());
        txtLastName.setText(person.getLastName());
        txtEmail.setText(person.getEmail());
        cbxPersonType.setValue(person.getPersonType());

        PersonType personType = person.getPersonType();
        if (personType.equals(PersonType.FISICA)) {
            txtRg.setText(((PessoaFisica) person).getRg());
            txtCpf.setText(((PessoaFisica) person).getCpf());
            disablePessoaJuridicaFields();
            return;
        } else if (personType.equals(PersonType.JURIDICA)) {
            txtCnpj.setText(((PessoaJuridica) person).getCnpj());
            disablePessoaFisicaFields();
            return;
        }

        throw new IllegalPersonTypeException("The person type parameter must be valid.");
    }

    private void getEntityFromView() {
        if (person == null) {
            createPerson();
        }

        person.setFirstName(txtFirstName.getText());
        person.setLastName(txtLastName.getText());
        person.setEmail(txtEmail.getText());
        PersonType personType = person.getPersonType();

        if (personType.equals(PersonType.FISICA)) {
            ((PessoaFisica) person).setRg(txtRg.getText());
            ((PessoaFisica) person).setCpf(txtCpf.getText());
        } else {
            ((PessoaJuridica) person).setCnpj(txtCnpj.getText());
        }
    }

    private void createPerson() {
        PersonType cbxPersonTypeValue = cbxPersonType.getValue();

        if (isPessoaFisica(cbxPersonTypeValue)) {
            PessoaFisica pessoaFisica = new PessoaFisica();
            pessoaFisica.setCpf(txtCpf.getText());
            pessoaFisica.setRg(txtRg.getText());
            person = pessoaFisica;
            return;
        }

        if (isPessoaJuridica(cbxPersonTypeValue)) {
            PessoaJuridica pessoaJuridica = new PessoaJuridica();
            pessoaJuridica.setCnpj(txtCnpj.getText());
            person = pessoaJuridica;
        }
    }

    private boolean isPessoaFisica(PersonType cbxPersonTypeValue) {
        return cbxPersonTypeValue.equals(PersonType.FISICA);
    }

    private boolean isPessoaJuridica(PersonType cbxPersonTypeValue) {
        return cbxPersonTypeValue.equals(PersonType.JURIDICA);
    }

    public void createPerson(ActionEvent actionEvent) throws IOException {
        getEntityFromView();
        createPersonUseCase.insert(person);
        WindowLoader.setRoot("PersonManagementUI");
    }

    public void updatePerson(ActionEvent actionEvent) throws IOException {
        getEntityFromView();
        updatePersonUseCase.update(person);
        WindowLoader.setRoot("PersonManagementUI");
    }


    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PersonManagementUI");
    }

    public void updatePersonTypeFields(ActionEvent actionEvent) {
        PersonType cbxPersonTypeValue = cbxPersonType.getValue();
        if (isPessoaFisica(cbxPersonTypeValue)) {
            enablePessoaFisicaTextFields();
            return;
        }

        if (isPessoaJuridica(cbxPersonTypeValue)) {
            enablePessoaJuridicaTextFields();
            return;
        }

        throw new IllegalPersonTypeException("The person type parameter must be valid.");
    }

    // TODO refactor -> create a local variable for PersonType and create a switch function that sets the proper
    //  fields and clear dirty data
    private void enablePessoaJuridicaTextFields() {
        txtCpf.setText("");
        txtRg.setText("");

        disablePessoaFisicaFields();
    }

    private void disablePessoaFisicaFields() {
        txtCpf.setDisable(true);
        txtRg.setDisable(true);
        txtCnpj.setDisable(false);
    }

    private void enablePessoaFisicaTextFields() {
        txtCnpj.setText("");

        disablePessoaJuridicaFields();
    }

    private void disablePessoaJuridicaFields() {
        txtCnpj.setDisable(true);
        txtCpf.setDisable(false);
        txtRg.setDisable(false);

    }


}
