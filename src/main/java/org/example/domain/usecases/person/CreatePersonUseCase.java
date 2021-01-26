package org.example.domain.usecases.person;

import org.example.domain.entities.person.Person;
import org.example.domain.utils.EntityAlreadyExistsException;
import org.example.domain.utils.Notification;
import org.example.domain.utils.Validator;


public class CreatePersonUseCase {
    private final PersonDAO personDAO;

    public CreatePersonUseCase(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public String insert(Person person) {
        Validator<Person> validator = new PersonInputRequestValidator();
        Notification notification = validator.validate(person);

        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        String email = person.getEmail();
        if (personDAO.findOneByEmail(email).isPresent()) {
            throw new EntityAlreadyExistsException("This email is already in user.");
        }

        return personDAO.create(person);
    }
}

