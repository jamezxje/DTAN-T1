package com.capstone.app.service.dash.impl;

import com.capstone.app.entity.Booking;
import com.capstone.app.entity.BookingDetail;
import com.capstone.app.service.dash.BookingService;
import com.capstone.app.util.DateTimeUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookingServiceImplTest {

    @Autowired
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void calculateFeePrice() {
        LocalDateTime pickupTime = LocalDateTime.now();
        LocalDateTime returnTime = pickupTime.plusDays(3).plusHours(2);
        double basePrice = 100.0;

        Booking booking = new Booking();
        BookingDetail bookingDetail = new BookingDetail();

        bookingDetail.setBasePrice(basePrice);
        booking.setBookingDetail(bookingDetail);

        booking.setBookingPickupTime(pickupTime);
        booking.setBookingReturnTime(returnTime);

        long diffDays = ChronoUnit.DAYS.between(booking.getBookingPickupTime(), booking.getBookingReturnTime());
        long diffHours = ChronoUnit.HOURS.between(booking.getBookingPickupTime(), booking.getBookingReturnTime());
        long diffSeconds = ChronoUnit.SECONDS.between(booking.getBookingPickupTime(), booking.getBookingReturnTime());

        System.out.println("diffDays: " + diffDays);
        System.out.println("diffHours: " + diffHours);
        System.out.println("diffSeconds: " + diffSeconds);


        long diffDays2 = diffSeconds/60/60/24; // diff days
        System.out.println("diffDays2: " + diffDays2);

        if (diffSeconds % (60*60*24) > 0) { // diff seconds
            diffDays2++;
            System.out.println( "Diff sec with % = " + diffSeconds % (60*60*24));
            System.out.println("diffDays2++: " + diffDays2);
        }

        System.out.println("Actual diff = " + diffDays2 );

        Double feePrice = booking.calculateFeePrice();

        assertEquals(60.0, feePrice);
    }

    @Test
    void calculateExtraFeePrice() {
    }

    @Test
    void calculateTotalDays() {
        Booking booking = new Booking();
        LocalDateTime pickupTime = LocalDateTime.now();
        LocalDateTime returnTime = pickupTime.plusDays(3).plusHours(2);
        long diffDays = DateTimeUtils.calculateTotalDays(pickupTime, returnTime);

        assertEquals(4, diffDays);

        returnTime = LocalDateTime.now();
        LocalDateTime actualReturnTime = returnTime.plusDays(1);
        LocalDateTime actualReturnTime2 = returnTime.minusDays(1);

        assertTrue(actualReturnTime.isAfter(returnTime));
        assertTrue(actualReturnTime2.isBefore(returnTime));
    }


}