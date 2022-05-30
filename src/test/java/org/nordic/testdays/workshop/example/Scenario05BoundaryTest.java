package org.nordic.testdays.workshop.example;

import org.junit.jupiter.api.Test;
import org.nordic.testdays.workshop.data.model.BookingRequest;
import org.nordic.testdays.workshop.validator.NumberOfPeopleValidator;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Boundary Testing
 */
public class Scenario05BoundaryTest {
    private final NumberOfPeopleValidator validator = new NumberOfPeopleValidator();
    private final LocalDateTime bookingDateTime = LocalDateTime.of(2022, 6, 5, 18, 0, 0);

    @Test
    void validate_peopleIs1_ReturnTrue() {
        BookingRequest bookingRequest = createBookingRequestFor(1);
        assertTrue(validator.validate(bookingRequest));
    }

    //This test should pass when validator is fixed
    @Test
    void validate_peopleIs0_ReturnFalse() {
        BookingRequest bookingRequest = createBookingRequestFor(0);
        assertFalse(validator.validate(bookingRequest));
    }

    @Test
    void validate_peopleIsNegative_ReturnFalse() {
        BookingRequest bookingRequest = createBookingRequestFor(-1);
        assertFalse(validator.validate(bookingRequest));
    }

    private BookingRequest createBookingRequestFor(int numberOfPeople) {
        return new BookingRequest("request1", bookingDateTime, "CrazyChicken", "member1", numberOfPeople);
    }
}
