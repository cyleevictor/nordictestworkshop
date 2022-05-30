package org.nordic.testdays.workshop.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nordic.testdays.workshop.MembershipSystem;
import org.nordic.testdays.workshop.RestaurantEnquiryService;
import org.nordic.testdays.workshop.data.model.BookingRequest;
import org.nordic.testdays.workshop.data.model.BookingStatus;
import org.nordic.testdays.workshop.data.model.Member;
import org.nordic.testdays.workshop.validator.BookingValidator;
import org.nordic.testdays.workshop.service.BookingServiceV2;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.nordic.testdays.workshop.data.model.BookingStatus.FAILED;
import static org.nordic.testdays.workshop.data.model.BookingStatus.SUCCESS;

/**
 * - Given - When - Then
 * - Use mocks under "Assert one thing"
 */
public class Scenario02Test {
    private final RestaurantEnquiryService restaurantEnquiryService = mock(RestaurantEnquiryService.class);
    private final MembershipSystem membershipService = mock(MembershipSystem.class);
    private final BookingValidator bookingValidator = mock(BookingValidator.class);
    private final BookingServiceV2 bookingService = new BookingServiceV2(restaurantEnquiryService, membershipService, bookingValidator);
    private final String memberId = "someMemberId";
    private final Member member1 = new Member(memberId, "Tony", "070-12345");
    private final LocalDateTime bookingDateTime = LocalDateTime.of(2022, 6, 5, 18, 0, 0);

    @BeforeEach
    void setUp() {
        when(restaurantEnquiryService.query("CrazyChicken")).thenReturn(true);
        when(membershipService.lookup(memberId)).thenReturn(member1);
    }

    @Test
    void addBooking_goodParams_Success() {
        //Given
        BookingRequest bookingRequest = givenBookingRequest(bookingDateTime, "CrazyChicken", memberId, 2);

        //When
        BookingStatus bookingStatus = bookingService.addBooking(bookingRequest);

        //Then:
        assertThat(bookingStatus, is(SUCCESS));
    }

    @Test
    void addBooking_unknownRestaurant_Failed() {
        //Given
        BookingRequest bookingRequest = givenBookingRequest(bookingDateTime, "ANONYMOUS", memberId, 2);

        //When
        BookingStatus bookingStatus = bookingService.addBooking(bookingRequest);

        //Then:
        assertThat(bookingStatus, is(FAILED));
    }

    private BookingRequest givenBookingRequest(LocalDateTime bookingDateTime, String restaurantId, String memberId, int numberOfPeople) {
        return new BookingRequest("request1", bookingDateTime, restaurantId, memberId, numberOfPeople);
    }

}
