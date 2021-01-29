package org.example.domain.entities.importation;

import org.example.domain.usecases.importation.ProductImportDAO;

import java.util.List;

public class FindImportedProductUseCase {
    private final ProductImportDAO productImportDAO;

    public FindImportedProductUseCase(ProductImportDAO productImportDAO) {
        this.productImportDAO = productImportDAO;
    }

    public List<ImportedProduct> findAll() {
        return productImportDAO.findAll();
    }
}
