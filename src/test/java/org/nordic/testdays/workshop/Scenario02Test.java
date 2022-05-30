package org.nordic.testdays.workshop;

import org.junit.jupiter.api.Test;
import org.nordic.testdays.workshop.data.model.BookingRequest;
import org.nordic.testdays.workshop.data.model.BookingStatus;
import org.nordic.testdays.workshop.validator.BookingValidator;
import org.nordic.testdays.workshop.service.BookingServiceV2;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.nordic.testdays.workshop.data.model.BookingStatus.FAILED;
import static org.nordic.testdays.workshop.data.model.BookingStatus.SUCCESS;

/**
 * - Given - When - Then
 * - Use mocks under "Assert one thing"
 */
public class Scenario02Test {
    private final RestaurantEnquiryService restaurantEnquiryService = new RestaurantEnquiryServiceImpl();
    private final MembershipSystem membershipService = new MembershipSystem();
    private final BookingValidator bookingValidator = new BookingValidator();
    private final BookingServiceV2 bookingService = new BookingServiceV2(restaurantEnquiryService, membershipService, bookingValidator);
    private LocalDateTime bookingDateTime;
    private BookingRequest bookingRequest;

    @Test
    void addBooking_goodParams_Success() {
        bookingDateTime = LocalDateTime.of(2022, 6, 5, 18, 0, 0);
        bookingRequest = new BookingRequest("request1", bookingDateTime, "CrazyChicken", "memberId1", 2);
        BookingStatus bookingStatus = bookingService.addBooking(bookingRequest);
        assertThat(bookingStatus, is(SUCCESS));
    }

    @Test
    void addBooking_unknownRestaurant_Failed() {
        bookingDateTime = LocalDateTime.of(2022, 6, 5, 18, 0, 0);
        bookingRequest = new BookingRequest("request1", bookingDateTime, "ANONYMOUS", "memberId1", 2);
        BookingStatus bookingStatus = bookingService.addBooking(bookingRequest);
        assertThat(bookingStatus, is(FAILED));
    }
}
