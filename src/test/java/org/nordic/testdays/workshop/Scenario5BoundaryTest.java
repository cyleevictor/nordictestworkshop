package org.nordic.testdays.workshop;

import org.junit.jupiter.api.Test;
import org.nordic.testdays.workshop.data.model.BookingRequest;
import org.nordic.testdays.workshop.validator.BookingValidator;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Boundary Testing
 */
public class Scenario5BoundaryTest {
    private final BookingValidator bookingValidator = new BookingValidator();
    private final LocalDateTime bookingDateTime = LocalDateTime.of(2022, 6, 5, 18, 0, 0);

    //TODO: create test cases to cover checking for different number of people: positive, zero, negative

    @Test
    void validate_peopleIs2_ReturnTrue() {
        BookingRequest bookingRequest = createBookingRequestFor(2);
        assertTrue(bookingValidator.validate(bookingRequest));
    }

    private BookingRequest createBookingRequestFor(int numberOfPeople) {
        return new BookingRequest("request1", bookingDateTime, "CrazyChicken", "member1", numberOfPeople);
    }
}
