package org.nordic.testdays.workshop.validator;

import org.nordic.testdays.workshop.data.model.BookingRequest;

public class BookingValidator {

    public boolean validate(BookingRequest request) {
        boolean result;

        if (request.getRestaurantId() == null || request.getRestaurantId().isEmpty()) {
            throw new RuntimeException("Invalid input for Restaurant");
        }
        //TODO: invalid checking here for membershipId
        if (request.getRequestId() == null  || request.getMembershipId().isEmpty()) {
            throw new RuntimeException("Invalid input for Membership ID");
        }

        if (request.getNumberOfPeople() <= 0) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }
}
