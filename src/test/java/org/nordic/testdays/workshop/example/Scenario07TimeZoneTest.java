package org.nordic.testdays.workshop.example;

import org.junit.jupiter.api.Test;
import org.nordic.testdays.workshop.validator.BookingTimeChecker;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Time Zone issues
 **/
public class Scenario07TimeZoneTest {
    private final BookingTimeChecker bookingTimeChecker = new BookingTimeChecker();

    @Test
    void isSpecialPromotionDate_Date0605_ReturnTrue() {
        LocalDateTime sunday0605 = LocalDateTime.of(2022, 6, 5, 0, 0);
        boolean specialPromotionDate = bookingTimeChecker.isSpecialPromotionDate(sunday0605);
        assertTrue(specialPromotionDate);
    }
}
