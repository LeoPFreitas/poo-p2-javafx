package org.example.domain.usecases.importation;

import org.example.domain.entities.importation.ImportedProduct;
import org.example.domain.entities.person.Person;
import org.example.domain.usecases.person.FindPersonUseCase;
import org.example.domain.utils.EntityNotFoundException;

public class ImportProductUseCase {
    private final ProductImportDAO productImportDAO;
    private final FindPersonUseCase findPersonUseCase;

    public ImportProductUseCase(ProductImportDAO productImportDAO, FindPersonUseCase findPersonUseCase) {
        this.productImportDAO = productImportDAO;
        this.findPersonUseCase = findPersonUseCase;
    }

    public Long importProduct(ImportedProduct importedProduct) {

        if (importedProduct == null) {
            throw new IllegalArgumentException("Imported product can not be null");
        }

        Long personId = importedProduct.getUserId();
        Person person = findPersonUseCase.findOne(personId)
                .orElseThrow(() -> new EntityNotFoundException("Can not found a person with ID " + personId));

//        Double dutiesPercentage = person.getImportDuties();
//        Double totalDuties = importedProduct.calcDuties(dutiesPercentage);
//        Double finalPrice = importedProduct.calcFinalPrice();
//
//        importedProduct.setTotalDuties(totalDuties);
//        importedProduct.setFinalPrice(finalPrice);

        Long importId = productImportDAO.create(importedProduct);

        person.addImportedProduct(importedProduct);

        return importId;
    }
}
