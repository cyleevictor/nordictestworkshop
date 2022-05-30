package org.nordic.testdays.workshop.service;

import org.nordic.testdays.workshop.MembershipSystem;
import org.nordic.testdays.workshop.RestaurantEnquiryService;
import org.nordic.testdays.workshop.data.model.BookingRecord;
import org.nordic.testdays.workshop.data.model.BookingRequest;
import org.nordic.testdays.workshop.data.model.BookingStatus;
import org.nordic.testdays.workshop.data.model.Member;
import org.nordic.testdays.workshop.validator.BookingValidator;

import java.util.HashMap;
import java.util.Map;

/**
 * Booking Service V2.
 * Add a booking records to the underlying cache from a BookingRequest
 * It will return a BookingStatus = SUCCESS when request could be processed,
 * or BookingStatus = FAILED when booking failed.
 */
public class BookingServiceV2 {
    private static final String ANONYMOUS = "Customer";
    private final Map<String, BookingRecord> bookingRecords = new HashMap<>();
    private final RestaurantEnquiryService restaurantEnquiryService;
    private final MembershipSystem membershipSystem;
    private final BookingValidator bookingValidator;

    public BookingServiceV2(RestaurantEnquiryService restaurantEnquiryService,
                            MembershipSystem membershipSystem,
                            BookingValidator bookingValidator) {
        this.restaurantEnquiryService = restaurantEnquiryService;
        this.membershipSystem = membershipSystem;
        this.bookingValidator = bookingValidator;
    }

    public BookingStatus addBooking(BookingRequest bookingRequest) {
        BookingRecord result;

        //Check if restaurant exists in the system
        boolean restaurantExists = restaurantEnquiryService.query(bookingRequest.getRestaurantId());

        //Check if the request is from member
        Member member = membershipSystem.lookup(bookingRequest.getMembershipId());

        //Create a BookingRecord and store it in cache, and return the booking status after validation
        if (restaurantExists) {
            if (isMember(member)) {
                //Let's assume we have some other actions for member
                result = createBookingRecord(bookingRequest, BookingStatus.SUCCESS, member.getName(), true);
            } else {
                result = createBookingRecord(bookingRequest, BookingStatus.SUCCESS, ANONYMOUS, false);
            }
            bookingRecords.put(bookingRequest.getRequestId(), result);
            return BookingStatus.SUCCESS;
        }
        return BookingStatus.FAILED;
    }

    public boolean exists(String requestId) {
        //TODO: Implement checking if request could be found from bookingRecords
        return false;
    }

    private BookingRecord createBookingRecord(BookingRequest bookingRequest, BookingStatus status, String bookedBy, boolean member) {
        return new BookingRecord(bookingRequest.getRequestId(),
                bookingRequest.getRestaurantId(),
                status,
                bookedBy,
                bookingRequest.getNumberOfPeople(),
                bookingRequest.getBookingDateTime().toString(),
                member);
    }

    private boolean isMember(Member member) {
        return member != null;
    }
}
