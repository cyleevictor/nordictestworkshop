package org.nordic.testdays.workshop.validator;

import java.time.*;

public class BookingTimeChecker {
    private LocalDateTime startDateTime = LocalDateTime.of(2022, 6, 4, 23, 59);
    private LocalDateTime endDateTime = LocalDateTime.of(2022, 6, 6, 0, 0);

    private ZonedDateTime zonedStartDateTime = ZonedDateTime.of(2022, 6, 4, 23, 59, 59, 0, ZoneId.of("UTC"));
    private ZonedDateTime zonedEndDateTime = ZonedDateTime.of(2022, 6, 6, 0, 0, 0, 0, ZoneId.of("UTC"));

    public boolean isSpecialPromotionDate(LocalDateTime bookingDateTime) {
        if (bookingDateTime.isAfter(startDateTime) && bookingDateTime.isBefore(endDateTime)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSpecialPromotionDate(ZonedDateTime bookingDateTime) {
        if (bookingDateTime.isAfter(zonedStartDateTime) && bookingDateTime.isBefore(zonedEndDateTime)) {
            return true;
        } else {
            return false;
        }
    }
}
