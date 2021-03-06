package org.nordic.testdays.workshop.example;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nordic.testdays.workshop.data.model.BookingRequest;
import org.nordic.testdays.workshop.service.BookingServiceV2;
import org.nordic.testdays.workshop.version3.AsyncBookingService;

import java.time.LocalDateTime;
import java.util.concurrent.Executor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Do not run multithreaded test
 */
public class Scenario10MultiThreadedTest {
    private final LocalDateTime bookingDateTime = LocalDateTime.of(2022, 6, 5, 18, 0, 0);
    private final BookingServiceV2 bookingService = mock(BookingServiceV2.class);
    private final BookingRequest bookingRequest = new BookingRequest("request1", bookingDateTime, "CrazyChicken", "member1", -1);
    private final Executor mockExecutor = mock(Executor.class);

    @BeforeEach
    void setUp() {
        doAnswer(invocation -> {
            invocation.getArgument(0, Runnable.class).run();
            return null;
        }).when(mockExecutor).execute(any());
    }

    @Test
    void bookAsync_withControlledRunnable() {
        Executor executor = Runnable::run;

        AsyncBookingService asyncBookingService = new AsyncBookingService(executor, bookingService);
        asyncBookingService.book(bookingRequest);
        verify(bookingService).addBooking(bookingRequest);
    }

    @Test
    void bookAsync_withMockedExecutor() {
        AsyncBookingService asyncBookingService = new AsyncBookingService(mockExecutor, bookingService);
        asyncBookingService.book(bookingRequest);
        verify(bookingService).addBooking(bookingRequest);
    }

    @Test
    void bookAsync_withGuavaMoreExecutors() {
        Executor executor = MoreExecutors.directExecutor();

        AsyncBookingService asyncBookingService = new AsyncBookingService(executor, bookingService);
        asyncBookingService.book(bookingRequest);
        verify(bookingService).addBooking(bookingRequest);
    }
}
