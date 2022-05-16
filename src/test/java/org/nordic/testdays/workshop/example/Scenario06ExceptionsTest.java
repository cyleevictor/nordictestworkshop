package org.nordic.testdays.workshop.example;

import org.junit.jupiter.api.Test;
import org.nordic.testdays.workshop.data.model.BookingRequest;
import org.nordic.testdays.workshop.validator.BookingValidator;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.instanceOf;
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
        RuntimeException exception = assertThrows(RuntimeException.class, () -> bookingValidator.validate(bookingRequest));
        assertThat(exception.getMessage(), is("Invalid input for Restaurant"));
    }

    //Fixed
    @Test
    void validate_RestaurantIsNull_ExceptionThrown_v2() {
        BookingRequest bookingRequest = new BookingRequest("request1", bookingDateTime, null, "memberId1", 2);
        try {
            bookingValidator.validate(bookingRequest);
            fail("Expect exception thrown when restaurantId is null");
        } catch (Exception e) {
            assertThat(e, is(instanceOf(RuntimeException.class)));
            assertThat(e.getMessage(), is("Invalid input for Restaurant"));
        }
    }

    //This test will be fixed with a given restaurantId, and production code fixed
    @Test
    void validate_MemberIdIsNull_ExceptionThrown() {
        BookingRequest bookingRequest = new BookingRequest("request1", bookingDateTime, "CrazyChicken", null, 2);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> bookingValidator.validate(bookingRequest));
       assertThat(exception.getMessage(), is("Invalid input for Membership ID"));
    }
}
