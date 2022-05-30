package org.nordic.testdays.workshop.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nordic.testdays.workshop.RestaurantEnquiryService;
import org.nordic.testdays.workshop.data.model.BookingRecord;
import org.nordic.testdays.workshop.data.model.BookingStatus;
import org.nordic.testdays.workshop.service.BookingService;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * - Assert one thing
 * - Naming of methods and variables
 */
public class Scenario01Test {
    private final LocalDateTime bookingDateTime =  LocalDateTime.of(2022, 6, 5, 18, 0, 0);
    private final RestaurantEnquiryService restaurantEnquiryService = mock(RestaurantEnquiryService.class);
    private final BookingService bookingService = new BookingService(restaurantEnquiryService);

    @BeforeEach
    void setUp() {
        when(restaurantEnquiryService.query("CrazyChicken")).thenReturn(true);
    }

    @Test
    void addBooking_goodParams_Success() {
        BookingRecord bookingRecord = bookingService.addBooking("CrazyChicken", "Tony", 3, bookingDateTime);
        assertBookingRecord(bookingRecord, "CrazyChicken", "Tony", 3, "2022-06-05T18:00", BookingStatus.SUCCESS);
    }

    @Test
    void addBooking_zeroPeople_Failed() {
        BookingRecord bookingRecord = bookingService.addBooking("CrazyChicken", "Helen", 0, bookingDateTime);
        assertBookingRecord(bookingRecord, "CrazyChicken", "Helen", 0, "2022-06-05T18:00", BookingStatus.FAILED);
    }

    @Test
    void addBooking_noName_Failed() {
        BookingRecord bookingRecord = bookingService.addBooking("CrazyChicken", null, 3, bookingDateTime);
        assertBookingRecord(bookingRecord, "CrazyChicken", null, 3, "2022-06-05T18:00", BookingStatus.FAILED);
    }

    @Test
    void addBooking_noBookingDateTime_Failed() {
        BookingRecord bookingRecord = bookingService.addBooking("CrazyChicken", "Tony", 1, null);
        assertThat(bookingRecord.getBookedBy(), is("Tony"));
        assertThat(bookingRecord.getPeople(), is(1));
        assertThat(bookingRecord.getTime(), is(nullValue()));
        assertThat(bookingRecord.getBookingStatus(), is(BookingStatus.FAILED));
    }

    private void assertBookingRecord(BookingRecord bookingRecord, String restaurantId, String name, int value, String bookedTime, BookingStatus success) {
        if (name != null) {
            assertThat(bookingRecord.getBookedBy(), is(name));
        } else {
            assertThat(bookingRecord.getBookedBy(), is(nullValue()));
        }
        assertThat(bookingRecord.getRestaurantId(), is(restaurantId));
        assertThat(bookingRecord.getPeople(), is(value));
        assertThat(bookingRecord.getTime(), is(bookedTime));
        assertThat(bookingRecord.getBookingStatus(), is(success));
    }
}
