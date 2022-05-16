package org.nordic.testdays.workshop;

import org.junit.jupiter.api.Test;
import org.nordic.testdays.workshop.data.model.BookingRequest;
import org.nordic.testdays.workshop.validator.BookingValidator;

import java.time.LocalDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Beware of random data
 */
public class Scenario08RandomTest {
    private BookingValidator validator = new BookingValidator();
    private final LocalDateTime bookingDateTime = LocalDateTime.of(2022, 6, 5, 18, 0, 0);

    @Test
    void validate_WithRandomNumOfPeople_ReturnTrue() {
        int randomNumber = generateRandomNumber();
        BookingRequest bookingRequest = new BookingRequest("requestId", bookingDateTime, "CrazyChicken", "12345", randomNumber);
        assertTrue(validator.validate(bookingRequest));
    }

    private int generateRandomNumber() {
        return new Random().nextInt();
    }

}
