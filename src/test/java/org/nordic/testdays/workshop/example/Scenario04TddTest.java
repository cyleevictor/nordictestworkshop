package org.nordic.testdays.workshop.example;

import org.junit.jupiter.api.Test;
import org.nordic.testdays.workshop.data.model.BookingRequest;
import org.nordic.testdays.workshop.validator.BuggyValidator;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * TDD for bugfix
 */
public class Scenario04TddTest {
    private final LocalDateTime bookingDateTime = LocalDateTime.of(2022, 6, 5, 18, 0, 0);
    private final BuggyValidator validator = new BuggyValidator();

    @Test
    void validate_zeroPeople_ReturnFalse() {
        BookingRequest bookingRequest = createBookingRequest("YummyYummy", 0);
        assertFalse(validator.validate(bookingRequest));
    }

    //This test should pass when production code is fixed
    @Test
    void validate_restaurantIsEmpty_ReturnFalse() {
        BookingRequest bookingRequest = createBookingRequest(null, 2);
        assertFalse(validator.validate(bookingRequest));
    }

    private BookingRequest createBookingRequest(String restaurantId, int numberOfPeople) {
        return new BookingRequest("requestId1", bookingDateTime, restaurantId, "memberId1", numberOfPeople);
    }
}
