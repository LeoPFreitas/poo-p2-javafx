package org.example.application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.application.view.WindowLoader;
import org.example.domain.entities.importation.ImportedProduct;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.example.application.main.Main.findImportedProductUseCase;

public class MainUIController {
    @FXML
    public TableView<ImportedProduct> tableView;
    @FXML
    public TableColumn<ImportedProduct, Long> cImportId;
    @FXML
    public TableColumn<ImportedProduct, Long> cPersonId;
    @FXML
    public TableColumn<ImportedProduct, String> cProductName;
    @FXML
    public TableColumn<ImportedProduct, Double> cTotalPrice;
    @FXML
    public TableColumn<ImportedProduct, LocalDate> cDate;

    private ObservableList<ImportedProduct> tableData;

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
        cImportId.setCellValueFactory(new PropertyValueFactory<>("productImportId"));
        cPersonId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        cProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        cTotalPrice.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        cDate.setCellValueFactory(new PropertyValueFactory<>("importDate"));
    }

    private void loadDataAndShow() {
        List<ImportedProduct> importedProductList = findImportedProductUseCase.findAll();
        tableData.clear();
        tableData.addAll(importedProductList);
    }

    public void findPerson(ActionEvent actionEvent) {

    }

    public void getSelectedAndSetButton(MouseEvent mouseEvent) {

    }

    public void managePerson(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PersonManagementUI");
    }

    public void startImportProcess(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ImportationUI");
    }
}
