package org.nordic.testdays.workshop;

import org.junit.jupiter.api.Test;
import org.nordic.testdays.workshop.data.model.BookingRecord;
import org.nordic.testdays.workshop.data.model.BookingStatus;
import org.nordic.testdays.workshop.version1.BookingService;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

/**
 * - Assert one thing
 * - Naming of methods and variables
 */
public class Scenario1Test {
    private final RestaurantEnquiryService restaurantEnquiryService = new RestaurantEnquiryServiceImpl();
    private final BookingService bookingService = new BookingService(restaurantEnquiryService);

    @Test
    void addingTest() {
        LocalDateTime date = LocalDateTime.of(2022, 6, 5, 18, 0, 0);
        BookingRecord br = bookingService.addBooking("CrazyChicken", "Tony", 3, date);
        assertThat(br.getBookedBy(), is("Tony"));
        assertThat(br.getPeople(), is(3));
        assertThat(br.getTime(), is("2022-06-05T18:00"));
        assertThat(br.getBookingStatus(), is(BookingStatus.SUCCESS));

        br = bookingService.addBooking("CrazyChicken", null, 3, date);
        assertThat(br.getBookedBy(), is(nullValue()));
        assertThat(br.getPeople(), is(3));
        assertThat(br.getTime(), is("2022-06-05T18:00"));
        assertThat(br.getBookingStatus(), is(BookingStatus.FAILED));

        br = bookingService.addBooking("CrazyChicken", "Tony", 0, date);
        assertThat(br.getBookedBy(), is("Tony"));
        assertThat(br.getPeople(), is(0));
        assertThat(br.getTime(), is("2022-06-05T18:00"));
        assertThat(br.getBookingStatus(), is(BookingStatus.FAILED));

        br = bookingService.addBooking("CrazyChicken", "Tony", 1, null);
        assertThat(br.getBookedBy(), is("Tony"));
        assertThat(br.getPeople(), is(1));
        assertThat(br.getTime(), is(nullValue()));
        assertThat(br.getBookingStatus(), is(BookingStatus.FAILED));
    }
}
