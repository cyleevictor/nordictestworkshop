package org.nordic.testdays.workshop.data.model;

import java.time.LocalDateTime;

public class BookingRequest {
    private final String requestId;
    private final LocalDateTime bookingDateTime;
    private final String restaurantId;
    private final String membershipId;
    private final int numberOfPeople;

    public BookingRequest(String requestId,
                          LocalDateTime bookingDateTime,
                          String restaurantId,
                          String membershipId,
                          int numberOfPeople) {
        this.requestId = requestId;
        this.bookingDateTime = bookingDateTime;
        this.restaurantId = restaurantId;
        this.membershipId = membershipId;
        this.numberOfPeople = numberOfPeople;
    }

    public String getRequestId() {
        return requestId;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }
}
