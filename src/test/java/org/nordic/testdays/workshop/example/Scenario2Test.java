package org.nordic.testdays.workshop.example;

import org.junit.jupiter.api.Test;
import org.nordic.testdays.workshop.MembershipSystem;
import org.nordic.testdays.workshop.RestaurantEnquiryService;
import org.nordic.testdays.workshop.data.model.BookingRequest;
import org.nordic.testdays.workshop.data.model.BookingStatus;
import org.nordic.testdays.workshop.data.model.Member;
import org.nordic.testdays.workshop.validator.BookingValidator;
import org.nordic.testdays.workshop.version2.BookingServiceV2;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.nordic.testdays.workshop.data.model.BookingStatus.SUCCESS;

/**
 * - Given - When - Then
 * - Use mocks under "Assert one thing"
 */
public class Scenario2Test {
    private final RestaurantEnquiryService restaurantEnquiryService = mock(RestaurantEnquiryService.class);
    private final MembershipSystem membershipService = mock(MembershipSystem.class);
    private final BookingValidator bookingValidator = mock(BookingValidator.class);
    private final BookingServiceV2 bookingService = new BookingServiceV2(restaurantEnquiryService, membershipService, bookingValidator);
    private final String memberId = "someMemberId";
    private final Member member1 = new Member(memberId, "Tony", "070-12345");
    private LocalDateTime bookingDateTime;
    private BookingRequest bookingRequest;

    @Test
    void addBooking_goodParams_Success() {
        //Given
        givenBookingDateTime();
        givenBookingRequest(bookingDateTime, "CrazyChicken", memberId, 2);

        //When
        when(restaurantEnquiryService.query("CrazyChicken")).thenReturn(true);
        when(membershipService.lookup(memberId)).thenReturn(member1);
        BookingStatus bookingStatus = bookingService.addBooking(bookingRequest);

        //Then:
        assertThat(bookingStatus, is(SUCCESS));
    }

    private void givenBookingRequest(LocalDateTime bookingDateTime, String restaurantId, String memberId, int numberOfPeople) {
        bookingRequest = new BookingRequest("request1", bookingDateTime, restaurantId, memberId, numberOfPeople);
    }

    private void givenBookingDateTime() {
        bookingDateTime = LocalDateTime.of(2022, 6, 5, 18, 0, 0);
    }

}
