package org.nordic.testdays.workshop.validator;

import org.nordic.testdays.workshop.data.model.BookingRequest;

public class BookingValidatorV2 {
    public boolean validate(BookingRequest request) {
        boolean result;

        if (request.getRestaurantId() == null || request.getRestaurantId().isEmpty()) {
            throw new RuntimeException("Invalid input for Restaurant");
        }
        //TODO: Fix this -- invalid checking here
        if (request.getRequestId() == null  || request.getMembershipId().isEmpty()) {
            throw new RuntimeException("Invalid input for Membership ID");
        }

        //TODO: Fix this -- should check people <=0 instead
        if (request.getNumberOfPeople() < 0) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }
}
