package org.nordic.testdays.workshop.version3;

import org.nordic.testdays.workshop.data.model.BookingRequest;
import org.nordic.testdays.workshop.version2.BookingServiceV2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public class AsyncBookingService {
    private final Executor executor;
    private final BookingServiceV2 bookingService;

    public AsyncBookingService(Executor executor, BookingServiceV2 bookingService) {
        this.executor = executor;
        this.bookingService = bookingService;
    }

    public void book(BookingRequest bookingRequest) throws ExecutionException, InterruptedException {
        CompletableFuture.runAsync(() -> {
            sleep(2000);
            bookingService.addBooking(bookingRequest);
        }, executor);
    }

    private void sleep(int timeInMs) {
        try {
            Thread.sleep(timeInMs);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
