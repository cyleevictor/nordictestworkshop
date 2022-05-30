package org.nordic.testdays.workshop.validator;

import org.nordic.testdays.workshop.data.model.BookingRequest;

public class NumberOfPeopleValidator {
    public boolean validate(BookingRequest request) {
        boolean result;

        //TODO: Fix this -- should check people <=0 instead
        if (request.getNumberOfPeople() < 0) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }
}
