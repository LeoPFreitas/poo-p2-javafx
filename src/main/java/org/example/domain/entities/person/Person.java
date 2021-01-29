package org.example.domain.entities.person;

import org.example.domain.entities.importation.ImportedProduct;

import java.util.ArrayList;
import java.util.List;

public abstract class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<ImportedProduct> importedProductList = new ArrayList<>();

    public Person() {
    }

    public Person(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public abstract Double getImportDuties();

    public abstract PersonType getPersonType();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public List<ImportedProduct> getImportedProductList() {
        return importedProductList;
    }

    public void addImportedProduct(ImportedProduct importedProduct) {
        this.importedProductList.add(importedProduct);
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}