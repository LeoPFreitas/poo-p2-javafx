package org.example.domain.usecases.person;

import org.example.domain.entities.person.Person;
import org.example.domain.utils.EntityNotFoundException;
import org.example.domain.utils.Notification;
import org.example.domain.utils.Validator;

public class UpdatePersonUseCase {
    private final PersonDAO personDAO;

    public UpdatePersonUseCase(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public boolean update(Person person) {
        Validator<Person> validator = new PersonInputRequestValidator();
        Notification notification = validator.validate(person);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        String id = longToStringConverter(person.getId());
        if (personDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Person not found.");

        return personDAO.update(person);
    }

    private String longToStringConverter(long id) {
        return String.valueOf(id);
    }

}
