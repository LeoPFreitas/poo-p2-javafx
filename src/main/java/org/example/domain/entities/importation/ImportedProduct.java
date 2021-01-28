package org.example.domain.entities.importation;

import org.example.domain.entities.person.Person;
import org.example.domain.utils.EntityNotFoundException;

import java.time.LocalDate;

import static org.example.application.main.Main.findPersonUseCase;

public class ImportedProduct {
    private ProductCategory productCategory;
    private String productName;
    private Double productPrice;
    private Double productWeightInKG;
    private LocalDate importDate;
    private Long id;
    private Long userId;
    private Double totalFreight;
    private Double totalDuties;
    private Double finalPrice;

    public ImportedProduct() {
    }

    public ImportedProduct(ProductCategory productCategory, String productName, Double productPrice,
                           Double productWeightInKG, Long userId) {
        this.productCategory = productCategory;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productWeightInKG = productWeightInKG;
        this.importDate = LocalDate.now();
        this.userId = userId;
        this.totalFreight = productWeightInKG * 13;
    }

    public ImportedProduct(ProductCategory productCategory, String productName, Double productPrice,
                           Double productWeightInKG, LocalDate importDate, Long id, Long userId) {
        this.productCategory = productCategory;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productWeightInKG = productWeightInKG;
        this.importDate = importDate;
        this.id = id;
        this.userId = userId;
        this.totalFreight = productWeightInKG * 13;
    }

    public Double calcDuties(Double totalFreight, Double totalProductPrice) {

        Person person = findPersonUseCase.findOne(this.userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + this.userId + " not founded."));

        return (totalFreight + totalProductPrice) * person.getImportDuties();
    }
    public Double calcFinalPrice(Double totalFreight, Double totalProductPrice, Double totalDuties) {
        return totalFreight + totalProductPrice + totalDuties;
    }


    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public String getProductName() {
        return productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public Double getProductWeightInKG() {
        return productWeightInKG;
    }

    public LocalDate getImportDate() {
        return importDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductWeightInKG(Double productWeightInKG) {
        this.productWeightInKG = productWeightInKG;
    }

    public void setImportDate(LocalDate importDate) {
        this.importDate = importDate;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getTotalDuties() {
        return totalDuties;
    }

    public void setTotalDuties(Double totalDuties) {
        this.totalDuties = totalDuties;
    }

    public Double getTotalFreight() {
        return totalFreight;
    }

    public void setTotalFreight(Double totalFreight) {
        this.totalFreight = totalFreight;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "ImpotedProduct{" +
                "productCategory=" + productCategory +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productWeightInKG=" + productWeightInKG +
                ", importDate=" + importDate +
                ", id=" + id +
                '}';
    }
}
