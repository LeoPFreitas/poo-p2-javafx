package org.example.application.repository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {
    public void buildDatabaseIfMissing() {
        if (isDatabaseMissing()) {
            System.out.println("Database is missing. Building database: \n");
            buildTables();
        }
    }

    private boolean isDatabaseMissing() {
        return !Files.exists(Paths.get("database.db"));
    }

    private void buildTables() {
        try (Statement statement = ConnectionFactory.createStatement()) {
            statement.addBatch(createPersonTable());
            statement.addBatch(createImportationTable());
            statement.executeBatch();

            System.out.println("Database successfully created.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private String createPersonTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE person (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("first_name TEXT NOT NULL, \n");
        builder.append("last_name TEXT NOT NULL, \n");
        builder.append("email TEXT NOT NULL UNIQUE, \n");
        builder.append("cnpj TEXT, \n");
        builder.append("cpf TEXT, \n");
        builder.append("rg TEXT \n");
        builder.append("); \n");
        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createImportationTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE importation (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("user INTEGER NOT NULL, \n");
        builder.append("product_name TEXT NOT NULL, \n");
        builder.append("category TEXT NOT NULL, \n");
        builder.append("product_price NUMERIC(14, 4) NOT NULL, \n");
        builder.append("product_wight INTEGER NOT NULL, \n");
        builder.append("date DATE NOT NULL, \n");
        builder.append("FOREIGN KEY(user) REFERENCES person(id) \n");
        builder.append("); \n");
        System.out.println(builder.toString());
        return builder.toString();
    }
}
