package org.example.domain.entities.importation;

import org.example.domain.entities.person.Person;

import java.time.LocalDate;

public class ImportedProduct {
    private ProductCategory productCategory;
    private String productName;
    private Double productPrice;
    private Double productWeightInKG;
    private LocalDate importDate;
    private Long id;
    private Person person;

    public ImportedProduct() {
    }

    public ImportedProduct(ProductCategory productCategory, String productName, Double productPrice,
                           Double productWeightInKG, Person person) {
        this.productCategory = productCategory;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productWeightInKG = productWeightInKG;
        this.importDate = LocalDate.now();
        this.person = person;
    }

    public ImportedProduct(ProductCategory productCategory, String productName, Double productPrice, Double productWeightInKG, LocalDate importDate, Long id, Person person) {
        this.productCategory = productCategory;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productWeightInKG = productWeightInKG;
        this.importDate = importDate;
        this.id = id;
        this.person = person;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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

    public int getUserId() {
        return 0;
    }
}
