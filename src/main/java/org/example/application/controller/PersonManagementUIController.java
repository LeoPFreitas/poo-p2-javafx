package org.example.application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.application.view.WindowLoader;
import org.example.domain.entities.person.Person;

import java.io.IOException;
import java.util.List;

import static org.example.application.main.Main.findPersonUseCase;
import static org.example.application.main.Main.removePersonUseCase;

public class PersonManagementUIController {
    @FXML
    public Button btnDelete;
    @FXML
    public Button btnEdit;
    @FXML
    public Button btnDetail;
    @FXML
    public Button btnNew;
    @FXML
    public Button btnBack;
    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person, Long> cId;
    @FXML
    private TableColumn<Person, String> cFirstName;
    @FXML
    private TableColumn<Person, String> cLastName;
    @FXML
    private TableColumn<Person, String> cEmail;
    @FXML
    private TableColumn<Person, String> cType;

    private ObservableList<Person> tableData;

    @FXML
    private void initialize() {
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }

    private void bindColumnsToValueSources() {
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        cLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        cEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cType.setCellValueFactory(new PropertyValueFactory<>("PersonType"));
    }

    private void loadDataAndShow() {
        List<Person> User = findPersonUseCase.findAll();
        tableData.clear();
        tableData.addAll(User);
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

    public void detailPerson(ActionEvent actionEvent) throws IOException {
        showUserInMode(UIMode.VIEW);
    }

    public void editPerson(ActionEvent actionEvent) throws IOException {
        showUserInMode(UIMode.UPDATE);
    }

    private void showUserInMode(UIMode mode) throws IOException {
        Person selectedPerson = tableView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            WindowLoader.setRoot("PersonUI");
            PersonUIController controller = (PersonUIController) WindowLoader.getController();
            controller.setUser(selectedPerson, mode);
        }
    }

    public void createPerson(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PersonUI");
    }
}
