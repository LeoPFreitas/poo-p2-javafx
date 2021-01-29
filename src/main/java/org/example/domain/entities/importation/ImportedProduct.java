package org.example.domain.entities.importation;

import java.time.LocalDate;

public class ImportedProduct {
    private ProductCategory productCategory;
    private String productName;
    private Double productPrice;
    private Double productWeightInKG;
    private LocalDate importDate;
    private Long productImportId;
    private Long userId;

    public ImportedProduct() {
    }

    public ImportedProduct(
            ProductCategory productCategory,
            String productName,
            Double productPrice,
            Double productWeightInKG,
            Long userId
    ) {
        this.productCategory = productCategory;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productWeightInKG = productWeightInKG;
        this.importDate = LocalDate.now();
        this.userId = userId;
    }

    public ImportedProduct(
            ProductCategory productCategory,
            String productName,
            Double productPrice,
            Double productWeightInKG,
            LocalDate date,
            Long userId
    ) {
        this.productCategory = productCategory;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productWeightInKG = productWeightInKG;
        this.importDate = date;
        this.userId = userId;
    }

    public ImportedProduct(
            ProductCategory productCategory,
            String productName, Double productPrice,
            Double productWeightInKG,
            LocalDate importDate,
            Long productImportId,
            Long userId
    ) {
        this.productCategory = productCategory;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productWeightInKG = productWeightInKG;
        this.importDate = importDate;
        this.productImportId = productImportId;
        this.userId = userId;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getProductWeightInKG() {
        return productWeightInKG;
    }

    public void setProductWeightInKG(Double productWeightInKG) {
        this.productWeightInKG = productWeightInKG;
    }

    public LocalDate getImportDate() {
        return importDate;
    }

    public void setImportDate(LocalDate importDate) {
        this.importDate = importDate;
    }

    public Long getProductImportId() {
        return productImportId;
    }

    public void setProductImportId(Long productImportId) {
        this.productImportId = productImportId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ImportedProduct{" +
                "productCategory=" + productCategory +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productWeightInKG=" + productWeightInKG +
                ", importDate=" + importDate +
                ", id=" + productImportId +
                '}';
    }

//    public Double calcDuties(Double totalFreight, Double totalProductPrice) {
//
//        Person person = findPersonUseCase.findOne(this.userId)
//                .orElseThrow(() -> new EntityNotFoundException("User with ID " + this.userId + " not founded."));
//
//        return (totalFreight + totalProductPrice) * person.getImportDuties();
//    }
//
//    public Double calcFinalPrice(Double totalFreight, Double totalProductPrice, Double totalDuties) {
//        return totalFreight + totalProductPrice + totalDuties;
//    }
}
