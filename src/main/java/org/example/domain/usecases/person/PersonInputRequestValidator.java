package org.example.domain.usecases.person;

import org.example.domain.entities.person.Person;
import org.example.domain.utils.Notification;
import org.example.domain.utils.Validator;

public class PersonInputRequestValidator extends Validator<Person> {

    @Override
    public Notification validate(Person person) {
        Notification notification = new Notification();

        if (person == null) {
            notification.addError("Person can not be null.");
            return notification;
        }

        if (nullOrEmpty(person.getFirstName())) {
            notification.addError("First name can not be null or empty.");
        }

        if (nullOrEmpty(person.getLastName())) {
            notification.addError("Last name can not be null ou empty.");
        }

        if (nullOrEmpty(person.getEmail())) {
            notification.addError("E-mail can not be null ou empty.");
        }

        return notification;
    }
}
