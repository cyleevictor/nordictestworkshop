package org.nordic.testdays.workshop.example;

import org.junit.jupiter.api.Test;
import org.nordic.testdays.workshop.data.model.BookingRequest;
import org.nordic.testdays.workshop.validator.BookingValidator;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Sensible error message
 */
public class Scenario3Test {
    private final BookingValidator bookingValidator = new BookingValidator();
    private final LocalDateTime bookingDateTime = LocalDateTime.of(2022, 6, 5, 18, 0, 0);

    @Test
    void validate_restaurantIsNull_ExceptionThrown() {
        BookingRequest bookingRequest = new BookingRequest("request1", bookingDateTime, null, "memberId1", 2);
        assertThrows(RuntimeException.class, () -> bookingValidator.validate(bookingRequest));
    }
}
