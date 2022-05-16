package org.nordic.testdays.workshop.data.model;

public class BookingRecord {
    private final String requestId;
    private final String restaurantId;
    private final BookingStatus bookingStatus;
    private final String bookedBy;
    private final int people;
    private final String time;
    private final boolean member;

    public BookingRecord(String requestId, String restaurantId, BookingStatus bookingStatus, String bookedBy, int people, String time, boolean member) {
        this.requestId = requestId;
        this.restaurantId = restaurantId;
        this.bookingStatus = bookingStatus;
        this.bookedBy = bookedBy;
        this.people = people;
        this.time = time;
        this.member = member;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public int getPeople() {
        return people;
    }

    public String getTime() {
        return time;
    }

    public boolean isMember() {
        return member;
    }
}
