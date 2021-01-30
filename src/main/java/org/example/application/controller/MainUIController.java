package org.example.application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.application.view.WindowLoader;
import org.example.domain.entities.importation.ImportedProduct;
import org.example.domain.utils.DateRange;
import org.example.domain.utils.IllegalDateRangeException;

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
    @FXML
    public Button btnFilter;
    @FXML
    public Button btnCLearFilter;
    @FXML
    public DatePicker dtpInitialDate;
    @FXML
    public DatePicker dtpEndDate;

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

    public void managePerson(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("PersonManagementUI");
    }

    public void startImportProcess(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("ImportationUI");
    }

    public void applyFilter(ActionEvent actionEvent) {
        try {
            validateInputFilterDate();
            DateRange dateRange = getDateRange();
            tableView.setItems(tableData
                    .filtered(data -> dateRange.includes(data.getImportDate())));
        } catch (IllegalDateRangeException e) {
            showAlert("Invalid Date Range", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void validateInputFilterDate() {
        if (dtpInitialDate.getValue() == null || dtpEndDate.getValue() == null) {
            throw new IllegalDateRangeException("Initial Date and/or Final Date can not be null");
        }
    }

    private DateRange getDateRange() {
        LocalDate startDate = dtpInitialDate.getValue();
        LocalDate endDate = dtpEndDate.getValue();
        DateRange dateRange = new DateRange(startDate, endDate);
        return dateRange;
    }

    public void clearFilter(ActionEvent actionEvent) {
        dtpInitialDate.setValue(null);
        dtpEndDate.setValue(null);
        tableView.setItems(tableData);
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
