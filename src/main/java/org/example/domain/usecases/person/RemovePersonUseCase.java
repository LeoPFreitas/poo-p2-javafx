package org.example.domain.usecases.person;

import org.example.domain.entities.person.Person;
import org.example.domain.utils.EntityNotFoundException;

public class RemovePersonUseCase {
    private final PersonDAO personDAO;

    public RemovePersonUseCase(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public boolean remove(long personId) {
        String id = longToStringConverter(personId);

        if (personDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("User not found.");
        }

        return personDAO.deleteByKey(id);
    }

    public boolean remove(Person user) {

        String userId = longToStringConverter(user.getId());

        if (personDAO.findOne(userId).isEmpty()) {
            throw new EntityNotFoundException("Book not found.");
        }

        return personDAO.delete(user);
    }

    private String longToStringConverter(long id) {
        return String.valueOf(id);
    }

}
