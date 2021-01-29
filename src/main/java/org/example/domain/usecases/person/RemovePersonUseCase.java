package org.example.domain.usecases.person;

import org.example.domain.entities.person.Person;
import org.example.domain.utils.EntityNotFoundException;
import org.example.domain.utils.IllegalPersonDeleteException;

public class RemovePersonUseCase {
    private final PersonDAO personDAO;

    public RemovePersonUseCase(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public boolean remove(long personId) {
        String id = longToStringConverter(personId);

        if (personDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Person not founded.");
        }

        return personDAO.deleteByKey(id);
    }

    public boolean remove(Person person) {

        String userId = longToStringConverter(person.getId());

        if (personDAO.findOne(userId).isEmpty()) {
            throw new EntityNotFoundException("Person not founded.");
        }

        if (person.getImportedProductCount() > 0) {
            throw new IllegalPersonDeleteException("Can not remove a person that have imported a product.");
        }

        return personDAO.delete(person);
    }

    private String longToStringConverter(long id) {
        return String.valueOf(id);
    }

}
