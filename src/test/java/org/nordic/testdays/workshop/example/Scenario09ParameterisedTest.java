package org.nordic.testdays.workshop.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.nordic.testdays.workshop.RestaurantEnquiryService;
import org.nordic.testdays.workshop.data.model.BookingRecord;
import org.nordic.testdays.workshop.data.model.BookingStatus;
import org.nordic.testdays.workshop.service.BookingService;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.nordic.testdays.workshop.data.model.BookingStatus.FAILED;
import static org.nordic.testdays.workshop.data.model.BookingStatus.SUCCESS;

/**
 * Parameterised Test
 */
public class Scenario09ParameterisedTest {
    private final RestaurantEnquiryService restaurantEnquiryService = mock(RestaurantEnquiryService.class);
    private final BookingService bookingService = new BookingService(restaurantEnquiryService);

    public static Stream<Arguments> provideBookingRequest() {
        LocalDateTime bookingDateTime1 = LocalDateTime.of(2022, 6, 5, 18, 0, 0);
        LocalDateTime bookingDateTime2 = LocalDateTime.of(2022, 6, 7, 11, 0, 0);
        LocalDateTime bookingDateTime3 = LocalDateTime.of(2022, 6, 6, 18, 0, 0);
        return Stream.of(
                Arguments.of("Tony", 2, bookingDateTime1, SUCCESS),
                Arguments.of(null, 3, bookingDateTime2, FAILED),
                Arguments.of("Helen", -1, bookingDateTime3, FAILED)
        );
    }

    @BeforeEach
    void setUp() {
        when(restaurantEnquiryService.query("CrazyChicken")).thenReturn(true);
    }

    @ParameterizedTest
    @MethodSource("provideBookingRequest")
    void addBooking_parameterised(String name, int people, LocalDateTime bookingDateTime, BookingStatus status) {
        BookingRecord br = bookingService.addBooking("CrazyChicken", name, people, bookingDateTime);
        assertThat(br.getBookedBy(), is(name));
        assertThat(br.getPeople(), is(people));
        assertThat(br.getTime(), is(bookingDateTime.toString()));
        assertThat(br.getBookingStatus(), is(status));
    }
}
