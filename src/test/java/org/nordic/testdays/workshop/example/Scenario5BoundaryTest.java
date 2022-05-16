package org.nordic.testdays.workshop.example;

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

    @Test
    void validate_peopleIs2_ReturnTrue() {
        BookingRequest bookingRequest = createBookingRequestFor(2);
        assertTrue(bookingValidator.validate(bookingRequest));
    }

    @Test
    void validate_peopleIs0_ReturnFalse() {
        BookingRequest bookingRequest = createBookingRequestFor(0);
        assertFalse(bookingValidator.validate(bookingRequest));
    }

    @Test
    void validate_peopleIsNegative_ReturnFalse() {
        BookingRequest bookingRequest = createBookingRequestFor(-1);
        assertFalse(bookingValidator.validate(bookingRequest));
    }

    private BookingRequest createBookingRequestFor(int numberOfPeople) {
        return new BookingRequest("request1", bookingDateTime, "CrazyChicken", "member1", numberOfPeople);
    }
}
