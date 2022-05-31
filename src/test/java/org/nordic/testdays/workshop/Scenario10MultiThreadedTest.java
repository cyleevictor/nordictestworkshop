package org.nordic.testdays.workshop;

import org.junit.jupiter.api.Test;
import org.nordic.testdays.workshop.data.model.BookingRequest;
import org.nordic.testdays.workshop.service.BookingServiceV2;
import org.nordic.testdays.workshop.version3.AsyncBookingService;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Do not run multithreaded test
 */
public class Scenario10MultiThreadedTest {
    private final LocalDateTime bookingDateTime = LocalDateTime.of(2022, 6, 5, 18, 0, 0);
    private final BookingServiceV2 bookingService = mock(BookingServiceV2.class);
    private final BookingRequest bookingRequest = new BookingRequest("request1", bookingDateTime, "CrazyChicken", "member1", -1);

    //TODO: This test will fail. How can we fix it?
    @Test
    void bookAsync_withSingleThreadPoolExecutor() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        AsyncBookingService asyncBookingService = new AsyncBookingService(executor, bookingService);
        asyncBookingService.book(bookingRequest);
        verify(bookingService).addBooking(bookingRequest);
    }
}
