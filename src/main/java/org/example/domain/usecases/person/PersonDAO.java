package org.example.domain.usecases.person;

import org.example.domain.entities.person.Person;
import org.example.domain.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface PersonDAO extends DAO<Person, String> {
    List<Person> findAllByLastName(String email);

    Optional<Person> findOneByEmail(String email);
}
