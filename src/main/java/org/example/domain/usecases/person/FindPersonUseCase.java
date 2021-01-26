package org.example.domain.usecases.person;

import org.example.domain.entities.person.Person;
import org.example.domain.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindPersonUseCase {
    private final PersonDAO personDAO;

    public FindPersonUseCase(PersonDAO userDAO) {
        this.personDAO = userDAO;
    }

    public Optional<Person> findOne(long personId) {
        String id = longToStringConverter(personId);

        if (Validator.nullOrEmpty(id))
            throw new IllegalArgumentException("ID can not be null.");
        return personDAO.findOne(id);
    }

    public Optional<Person> findOneByEmail(String email) {
        if (Validator.nullOrEmpty(email))
            throw new IllegalArgumentException("Email can not be null or empty.");
        return personDAO.findOneByEmail(email);
    }

    public List<Person> findAll() {
        return personDAO.findAll();
    }

    private String longToStringConverter(long id) {
        return String.valueOf(id);
    }
}
