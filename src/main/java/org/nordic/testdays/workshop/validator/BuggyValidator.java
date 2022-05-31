package org.nordic.testdays.workshop.validator;

import org.nordic.testdays.workshop.data.model.BookingRequest;

public class BuggyValidator {

    //TODO: Fix this method to make test passes
    public boolean validate(BookingRequest request) {
        boolean result;

        if (request.getRestaurantId() == null || request.getRestaurantId().isEmpty()) {
            result = false;
        }

        if (request.getNumberOfPeople() < 1) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }
}
