package org.nordic.testdays.workshop;

import org.junit.jupiter.api.Test;
import org.nordic.testdays.workshop.validator.BookingTimeChecker;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Time Zone issues
 **/
public class Scenario7TimeZoneTest {
    private final BookingTimeChecker bookingTimeChecker = new BookingTimeChecker();
    LocalDateTime sunday0605 = LocalDateTime.of(2022, 6, 5, 0, 0);

    //Test that failed on different servers (change to Asian timezone will make this fail)
    //Try using LocalDateTime instead
    @Test
    void isSpecialPromotionDate_Date0605_ReturnTrue() throws ParseException {
        ZonedDateTime zonedBookingDateTime = getZonedDateTime("20220605-01:00:00");
        assertTrue(bookingTimeChecker.isSpecialPromotionDate(zonedBookingDateTime));
    }

    private ZonedDateTime getZonedDateTime(String bookingTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm:ss").withZone(ZoneId.systemDefault());
        ZonedDateTime zonedBookingDateTime = ZonedDateTime.from(dateTimeFormatter.parse(bookingTimeStr));
        return zonedBookingDateTime;
    }
}
