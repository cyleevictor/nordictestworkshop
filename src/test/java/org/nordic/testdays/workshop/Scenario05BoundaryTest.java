package org.nordic.testdays.workshop;

import org.junit.jupiter.api.Test;
import org.nordic.testdays.workshop.data.model.BookingRequest;
import org.nordic.testdays.workshop.validator.NumberOfPeopleValidator;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Boundary Testing
 */
public class Scenario05BoundaryTest {
    private final NumberOfPeopleValidator validator = new NumberOfPeopleValidator();
    private final LocalDateTime bookingDateTime = LocalDateTime.of(2022, 6, 5, 18, 0, 0);

    //TODO: create test cases to cover checking for different number of people: positive, zero, negative
    @Test
    void validate_peopleIs2_ReturnTrue() {
        BookingRequest bookingRequest = createBookingRequestFor(2);
        assertTrue(validator.validate(bookingRequest));
    }

    private BookingRequest createBookingRequestFor(int numberOfPeople) {
        return new BookingRequest("request1", bookingDateTime, "CrazyChicken", "member1", numberOfPeople);
    }
}
