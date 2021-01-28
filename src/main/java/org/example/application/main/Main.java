package org.example.application.main;

import org.example.application.repository.DatabaseBuilder;
import org.example.application.repository.SqlitePersonDAO;
import org.example.application.repository.SqliteProductImportDAO;
import org.example.application.view.WindowLoader;
import org.example.domain.usecases.importation.ImportProductUseCase;
import org.example.domain.usecases.importation.ProductImportDAO;
import org.example.domain.usecases.person.*;

public class Main {

    public static CreatePersonUseCase createPersonUseCase;
    public static UpdatePersonUseCase updatePersonUseCase;
    public static FindPersonUseCase findPersonUseCase;
    public static RemovePersonUseCase removePersonUseCase;
    public static ImportProductUseCase importProductUseCase;


    public static void main(String[] args) {
        configureInjection();
        setupDatabase();
        WindowLoader.main(args);
    }

    private static void setupDatabase() {
        DatabaseBuilder dbBuilder = new DatabaseBuilder();
        dbBuilder.buildDatabaseIfMissing();
    }

    private static void configureInjection() {
        PersonDAO personDAO = new SqlitePersonDAO();
        createPersonUseCase = new CreatePersonUseCase(personDAO);
        updatePersonUseCase = new UpdatePersonUseCase(personDAO);
        findPersonUseCase = new FindPersonUseCase(personDAO);
        removePersonUseCase = new RemovePersonUseCase(personDAO);

        ProductImportDAO productImportDAO = new SqliteProductImportDAO();
        importProductUseCase = new ImportProductUseCase(productImportDAO);
    }
}
