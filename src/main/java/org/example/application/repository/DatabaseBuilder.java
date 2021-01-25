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
        builder.append("firstName TEXT NOT NULL, \n");
        builder.append("lastName TEXT NOT NULL, \n");
        builder.append("email TEXT NOT NULL UNIQUE, \n");
        builder.append("cnpj TEXT, \n");
        builder.append("cpf TEXT, \n");
        builder.append("rg TEXT \n");
        builder.append("); \n");
        System.out.println(builder.toString());
        return builder.toString();
    }
}
