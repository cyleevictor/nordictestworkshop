package org.nordic.testdays.workshop;

import org.junit.jupiter.api.Test;
import org.nordic.testdays.workshop.data.model.BookingRequest;
import org.nordic.testdays.workshop.validator.BookingValidator;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing for Exceptions
 */
public class Scenario06ExceptionsTest {
    private final BookingValidator bookingValidator = new BookingValidator();
    private final LocalDateTime bookingDateTime = LocalDateTime.of(2022, 6, 5, 18, 0, 0);

    @Test
    void validate_RestaurantIsNull_ExceptionThrown() {
        BookingRequest bookingRequest = new BookingRequest("request1", bookingDateTime, null, "memberId1", 2);
        assertThrows(RuntimeException.class, () -> bookingValidator.validate(bookingRequest));
    }

    //TODO: This is expected to fail as test setup is wrong. How can we fix this?
    @Test
    void validate_MemberIdIsNull_ExceptionThrown() {
        BookingRequest bookingRequest = new BookingRequest("request1", bookingDateTime, null, null, 2);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> bookingValidator.validate(bookingRequest));
        assertThat(exception.getMessage(), is("Invalid input for Membership ID"));
    }

}
