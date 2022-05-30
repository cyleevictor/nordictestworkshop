package org.nordic.testdays.workshop.validator;

import java.time.*;

public class BookingTimeChecker {
    private ZonedDateTime zonedStartDateTime = ZonedDateTime.of(2022, 6, 5, 11, 00, 00, 0, ZoneId.of("UTC"));
    private ZonedDateTime zonedEndDateTime = ZonedDateTime.of(2022, 6, 5, 13, 0, 0, 0, ZoneId.of("UTC"));

    public boolean isSpecialPromotion(ZonedDateTime bookingDateTime) {
        if (bookingDateTime.isAfter(zonedStartDateTime) && bookingDateTime.isBefore(zonedEndDateTime)) {
            return true;
        } else {
            return false;
        }
    }
}
