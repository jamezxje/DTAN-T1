package com.capstone.app.service.front;

import com.capstone.app.entity.Booking;
import com.capstone.app.entity.type.Status;
import com.capstone.app.repository.CarRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FrontCarServiceTest {

    @Autowired
    FrontCarService frontCarService;
    @Autowired
    FrontBookingService frontBookingService;

    @Test
    void isCarAvailableToBooking() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusDays(2);
        Integer carId = 10;

        List<Booking> bookings = frontBookingService.findBookingsByCarAndTime(Status.APPROVED, Status.APPROVED, carId, start, end);
        System.out.println(bookings.size());

        boolean result = frontCarService.isCarAvailableToBooking(carId, start, end);
        assertTrue(result && bookings.size() == 1);
    }

    @Test
    void testIsCarAvailableToBooking() {
    }
}