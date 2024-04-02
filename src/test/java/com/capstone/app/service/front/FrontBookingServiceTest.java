package com.capstone.app.service.front;

import com.capstone.app.entity.Booking;
import com.capstone.app.entity.Car;
import com.capstone.app.entity.type.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static com.capstone.app.config.DataSeeder.CARS_SEED;

@SpringBootTest
class FrontBookingServiceTest {

    @Autowired
    private FrontBookingService frontBookingService;

    @Test
    void isCarAvailable() {
        Car car = CARS_SEED.get(1);
        LocalDateTime start = LocalDateTime.of(2024, 3, 14, 4, 18);
        LocalDateTime end = LocalDateTime.of(2024, 3, 28, 10, 0);

        assert frontBookingService.isCarAvailable(car.getCarId(), start, end);

        List<Booking> bookingList = frontBookingService.findBookingsByCarAndTime(Status.APPROVED, Status.APPROVED, car.getCarId(), start, end);

        for (Booking booking : bookingList) {
            System.out.println(booking.getBookingId()
                    + " " + booking.getBookingPickupTime()
                    + " " + booking.getBookingReturnTime()
                    + " " + booking.getBookingActualReturnTime()
                    + " " + booking.getCarModel());
        }

        boolean result = frontBookingService.isCarAvailable(car.getCarId(), start, end);

        assert ! result && bookingList.size() == 1;
    }

}