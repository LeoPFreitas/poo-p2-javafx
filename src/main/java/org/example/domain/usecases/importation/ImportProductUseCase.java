package org.example.domain.usecases.importation;

import org.example.domain.entities.importation.ImportedProduct;

public class ImportProductUseCase {
    private final ProductImportDAO productImportDAO;

    public ImportProductUseCase(ProductImportDAO productImportDAO) {
        this.productImportDAO = productImportDAO;
    }

    public Long importProduct(ImportedProduct importedProduct) {

        if (importedProduct == null) {
            throw new IllegalArgumentException("Imported product can not be null");
        }

        Double totalFreight = importedProduct.getTotalFreight();
        Double productPrice = importedProduct.getProductPrice();
        Double duties = importedProduct.calcDuties(totalFreight, productPrice);
        Double finalPrice = importedProduct.calcFinalPrice(totalFreight, productPrice, duties);

        importedProduct.setTotalDuties(duties);
        importedProduct.setFinalPrice(finalPrice);

        return productImportDAO.create(importedProduct);
    }
}
