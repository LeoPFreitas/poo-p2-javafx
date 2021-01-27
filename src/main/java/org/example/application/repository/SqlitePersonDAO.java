package org.example.application.repository;

import org.example.domain.entities.person.Person;
import org.example.domain.entities.person.PessoaFisica;
import org.example.domain.entities.person.PessoaJuridica;
import org.example.domain.usecases.person.PersonDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlitePersonDAO implements PersonDAO {


    @Override
    public String create(Person person) {
        String sql = "INSERT INTO Person(first_name, last_name, email, cnpj, cpf, rg) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.setString(3, person.getEmail());

            if (person instanceof PessoaFisica) {
                stmt.setString(5, ((PessoaFisica) person).getCpf());
                stmt.setString(6, ((PessoaFisica) person).getRg());
                stmt.setNull(4, Types.VARCHAR);
            }

            if (person instanceof PessoaJuridica) {
                stmt.setString(4, ((PessoaJuridica) person).getCnpj());
                stmt.setNull(5, Types.VARCHAR);
                stmt.setNull(6, Types.VARCHAR);
            }

            stmt.execute();
            return String.valueOf(person.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Person> findOne(String key) {
        return findOneByAttribute("id", key);
    }

    @Override
    public Optional<Person> findOneByEmail(String email) {
        return findOneByAttribute("email", email);
    }

    private Optional<Person> findOneByAttribute(String attribute, String value) {
        String sql = "SELECT * FROM person WHERE " + attribute + "  = ?";
        Person person = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                person = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(person);
    }

    // TODO refactor for rem duplicated code
    @Override
    public List<Person> findAllByLastName(String lastName) {
        List<Person> personList = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE " + lastName + " = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Person person = resultSetToEntity(resultSet);
                personList.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    @Override
    public List<Person> findAll() {
        List<Person> personList = new ArrayList<>();
        String sql = "SELECT * FROM person";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Person person = resultSetToEntity(resultSet);
                personList.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }


    private Person resultSetToEntity(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String email = rs.getString("email");

        String cnpj = rs.getString("cnpj");
        String cpf = rs.getString("cpf");
        String rg = rs.getString("rg");


        if (cnpj != null) {
            return new PessoaJuridica(id, firstName, lastName, email, cnpj);
        } else {
            return new PessoaFisica(id, firstName, lastName, email, cpf, rg);
        }
    }

    @Override
    public boolean update(Person person) {
        String sql = "UPDATE person SET first_name = ?, last_name = ? , email = ?, cnpj = ?, cpf = ?, rg = ? WHERE id" +
                " = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.setString(3, person.getEmail());

            if (person instanceof PessoaFisica) {
                stmt.setNull(4, Types.VARCHAR);
                stmt.setString(5, ((PessoaFisica) person).getCpf());
                stmt.setString(6, ((PessoaFisica) person).getRg());
            }

            if (person instanceof PessoaJuridica) {
                stmt.setString(4, ((PessoaJuridica) person).getCnpj());
                stmt.setNull(5, Types.VARCHAR);
                stmt.setNull(6, Types.VARCHAR);
            }

            stmt.setString(7, String.valueOf(person.getId()));

            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(String id) {
        String sql = "DELETE FROM person WHERE id = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, id);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Person can not be null.");
        }

        String id = String.valueOf(person.getId());
        return deleteByKey(id);
    }
}
