package org.example.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.example.application.view.WindowLoader;
import org.example.domain.entities.person.Person;

import java.io.IOException;

import static org.example.application.main.Main.removePersonUseCase;

public class PersonUIController {
    @FXML
    private TableView<Person> tableView;


    @FXML
    private void initialize() {
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {

    }

    private void bindColumnsToValueSources() {

    }

    private void loadDataAndShow() {

    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("MainUI");
    }

    public void deletePerson(ActionEvent actionEvent) {
        Person selectedUser = tableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            removePersonUseCase.remove(selectedUser);
            loadDataAndShow();
        }
    }

    public void detailPerson(ActionEvent actionEvent) {

    }

    public void editPerson(ActionEvent actionEvent) {

    }

    public void createPerson(ActionEvent actionEvent) {

    }
}
