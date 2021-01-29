package org.example.application.repository;

import org.example.domain.entities.importation.ImportedProduct;
import org.example.domain.entities.importation.ProductCategory;
import org.example.domain.usecases.importation.ProductImportDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteProductImportDAO implements ProductImportDAO {

    @Override
    public Long create(ImportedProduct importedProduct) {
        String sql = "INSERT INTO importation(user, product_name, product_price, product_wight, date, category) VALUES (?, ?," +
                "?, ?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setLong(1, importedProduct.getUserId());
            stmt.setString(2, importedProduct.getProductName());
            stmt.setDouble(3, importedProduct.getProductPrice());
            stmt.setDouble(4, importedProduct.getProductWeightInKG());
            stmt.setString(5, importedProduct.getImportDate().toString());
            stmt.setString(6, importedProduct.getProductCategory().toString());

            stmt.execute();

            ResultSet resultSet = stmt.getGeneratedKeys();
            Long generatedId = resultSet.getLong(1);
            return generatedId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ImportedProduct> findAll() {
        String sql = "SELECT * FROM importation";
        List<ImportedProduct> importedProductList = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ImportedProduct importedProduct = resultSetToEntity(rs);
                importedProductList.add(importedProduct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return importedProductList;
    }

    @Override
    public Optional<ImportedProduct> findOne(Long key) {
        String sql = "SELECT * FROM importation WHERE id = ?";
        ImportedProduct importedProduct = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setLong(1, key);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                importedProduct = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(importedProduct);
    }

    private ImportedProduct resultSetToEntity(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String date = rs.getString("date");
        LocalDate convertedDate = null;

        if (date != null)
            convertedDate = LocalDate.parse(date);

        return new ImportedProduct(
                ProductCategory.toEnum(rs.getString("category")),
                rs.getString("product_name"),
                rs.getDouble("product_price"),
                rs.getDouble("product_weight"),
                convertedDate,
                id
        );
    }

    @Override
    public boolean update(ImportedProduct importedProduct) {
        String sql = "UPDATE importation SET user = ?, product_name = ?, product_price = ?, product_wight = ?, date =" +
                " ?, category = ? WHERE id = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, importedProduct.getProductName());
            stmt.setDouble(2, importedProduct.getProductPrice());
            stmt.setDouble(3, importedProduct.getProductWeightInKG());
            stmt.setString(4, importedProduct.getImportDate().toString());
            stmt.setString(5, importedProduct.getProductCategory().toString());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Long key) {
        String sql = "DELETE FROM importation WHERE id = ?";
        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setLong(1, key);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(ImportedProduct importedProduct) {
        if (importedProduct == null || importedProduct.getProductImportId() == null)
            throw new IllegalArgumentException("ImportedProduct ID must not be null.");
        return deleteByKey(importedProduct.getProductImportId());
    }
}
