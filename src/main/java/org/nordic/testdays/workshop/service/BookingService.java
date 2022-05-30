package org.nordic.testdays.workshop.service;

import org.nordic.testdays.workshop.RestaurantEnquiryService;
import org.nordic.testdays.workshop.data.model.BookingRecord;
import org.nordic.testdays.workshop.data.model.BookingStatus;
import org.nordic.testdays.workshop.validator.BookingValidator;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class BookingService {
    private final RestaurantEnquiryService restaurantEnquiryService;

    public BookingService(RestaurantEnquiryService restaurantEnquiryService) {
        this.restaurantEnquiryService = restaurantEnquiryService;
    }

    public BookingRecord addBooking(String restaurantId, String name, int people, LocalDateTime bookingDateTime) {
        if (bookingDateTime == null) {
            return new BookingRecord("requestId", restaurantId, BookingStatus.FAILED, name, people, null, false);
        }
        if (people <= 0) {
            return new BookingRecord("requestId", restaurantId, BookingStatus.FAILED, name, people, bookingDateTime.toString(), false);
        }
        if (name == null) {
            return new BookingRecord("requestId", restaurantId, BookingStatus.FAILED, null, people, bookingDateTime.toString(), false);
        }
        if (!restaurantEnquiryService.query(restaurantId)) {
            return new BookingRecord("requestId", null, BookingStatus.FAILED, name, people, bookingDateTime.toString(), false);
        }
        return new BookingRecord("requestId", restaurantId, BookingStatus.SUCCESS, name, people, bookingDateTime.toString(), false);
    }

}
