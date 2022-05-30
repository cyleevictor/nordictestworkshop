package org.nordic.testdays.workshop.example;

import org.junit.jupiter.api.Test;
import org.nordic.testdays.workshop.validator.BookingTimeChecker;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Time Zone issues
 **/
public class Scenario07TimeZoneTest {
    private final BookingTimeChecker bookingTimeChecker = new BookingTimeChecker();

    @Test
    void isSpecialPromotion_TimeIs12_30_ReturnTrue() {
        ZonedDateTime zonedBookingDateTime = getZonedDateTime("20220605-12:30:00");
        assertTrue(bookingTimeChecker.isSpecialPromotion(zonedBookingDateTime));
    }

    private ZonedDateTime getZonedDateTime(String bookingTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm:ss").withZone(ZoneId.of("UTC"));
        ZonedDateTime zonedBookingDateTime = ZonedDateTime.from(dateTimeFormatter.parse(bookingTimeStr));
        return zonedBookingDateTime;
    }
}
