package org.example.domain.usecases.user;

import org.example.domain.entities.user.Person;
import org.example.domain.utils.DAO;

import java.util.Optional;

public interface PersonDAO extends DAO<Person, String> {
    Optional<Person> findOneByFirstAndLastName(String email);
}
