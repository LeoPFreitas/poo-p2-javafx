package org.example.domain.usecases.importation;

import org.example.domain.entities.importation.ImportedProduct;
import org.example.domain.entities.person.Person;

import java.util.List;

import static org.example.application.main.Main.findPersonUseCase;

public class FindImportedProductUseCase {
    private final ProductImportDAO productImportDAO;

    public FindImportedProductUseCase(ProductImportDAO productImportDAO) {
        this.productImportDAO = productImportDAO;
    }

    public List<ImportedProduct> findAll() {
        List<ImportedProduct> importedProductList = productImportDAO.findAll();
        importedProductList.forEach(this::bindCalculatedFields);
        return importedProductList;

    }

    private void bindCalculatedFields(ImportedProduct p) {
        Double dutiesRate = getDutiesRate(p.getUserId());
        p.setTotalDuties(p.calcDuties(dutiesRate));
        p.setFinalPrice(p.calcFinalPrice());
    }

    private Double getDutiesRate(Long id) {
        Person person = findPersonUseCase.findOne(id).get();
        return person.getImportDuties();
    }
}
