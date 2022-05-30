package org.nordic.testdays.workshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import static org.nordic.testdays.workshop.data.model.BookingStatus.SUCCESS;

/**
 * Sensible error message
 */
public class Scenario03Test {
    private final BookingValidator bookingValidator = new BookingValidator();
    private final RestaurantEnquiryService restaurantEnquiryService = mock(RestaurantEnquiryService.class);
    private final MembershipSystem membershipService = mock(MembershipSystem.class);
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
    void validate_BookingRecordExists_returnTrue() {
        //TODO: This test failed as production code not implemented. Consider giving an explanation for expected result in assert statement
        //Given
        BookingRequest bookingRequest = givenBookingRequest("request1", bookingDateTime, "CrazyChicken", memberId, 2);
        giveRequestAddedSuccessfully(bookingRequest);

        //When
        boolean recordFound = bookingService.exists("request1");

        //Then
        assertThat(recordFound, is(true));
    }

    private void giveRequestAddedSuccessfully(BookingRequest bookingRequest) {
        BookingStatus bookingStatus = bookingService.addBooking(bookingRequest);
        assertThat(bookingStatus, is(SUCCESS));
    }

    private BookingRequest givenBookingRequest(String request, LocalDateTime bookingDateTime, String restaurantId, String memberId, int numberOfPeople) {
        return new BookingRequest(request, bookingDateTime, restaurantId, memberId, numberOfPeople);
    }
}
