package com.capstone.app.config;

import com.capstone.app.entity.SystemOption;
import com.capstone.app.repository.*;
import com.capstone.app.service.common.CommonDataService;
import com.capstone.app.service.common.SystemOptionService;
import com.capstone.app.service.front.FrontBookingService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.capstone.app.config.DataSeeder.MEMBERS_SEED;

/**
 * This class is used to initialize the database with some default data.
 */

@Component
@RequiredArgsConstructor
@Getter
@DependsOn("commonDataService")
public class ApplicationStartupRunner implements CommandLineRunner {

    private final SystemOptionService systemOptionService;
    private Map<String, String> optionMap;
    private final MemberRepo memberRepo;
    private final BrandRepo brandRepo;
    private final ImageRepo imageRepo;
    private final CarRepo carRepo;
    private final SystemOptionRepo systemOptionRepo;
    private final TransactionRepo transactionRepo;
    private final BookingRepo bookingRepo;
    private final FeedbackRepo feedbackRepo;
    private final FrontBookingService frontBookingService;
    private final CommonDataService commonDataService;
    private final PostRepo postRepo;

    @Override
    public void run(String... args) {

//        memberRepo.saveAll(MEMBERS_SEED);
//        postRepo.saveAll(POSTS_SEED);
//        imageRepo.saveAll(IMAGES_SEED);
//        brandRepo.saveAll(BRANDS_SEED);
//        carRepo.saveAll(CARS_SEED);
//        systemOptionRepo.saveAll(SYSTEM_OPTION_SEED);
//
//        transactionRepo.saveAll(DEPOSIT_REQUESTS_SEED);

//        feedbackRepo.saveAll(FEEDBACKS_SEED);

//        List<Booking> bookings = new ArrayList<>();
//
//        for(int i = 0; i< BOOKING_REQUEST_DTOS_SEED.size(); i++) {
//
//            Booking booking = BOOKING_REQUEST_DTOS_SEED.get(i).toEntity();
//
//            Car car = CARS_SEED.get(i);
//
//            booking.setCar(car);
//            booking.setMember(MEMBERS_SEED.get(1));
//            booking.setCarModel(car.getCarModel());
//
//            bookings.add(booking);
//        }

//        bookings.get(1).setMember(MEMBERS_SEED.get(3));
//        bookings.get(3).setMember(MEMBERS_SEED.get(3));
//        bookings.get(4).setMember(MEMBERS_SEED.get(3));
//        bookings.get(9).setMember(MEMBERS_SEED.get(3));
//        bookings.get(8).setMember(MEMBERS_SEED.get(3));
//
//        for (int i=0; i< BOOKING_SEED.size(); i++){
//            Booking booking = BOOKING_SEED.get(i);
//            BookingDetail bookingDetail = BOOKING_DETAILS_SEED.get(i);
//            booking.setBookingDetail(bookingDetail);
//            bookings.add(booking);
//        }
//
//        bookingRepo.saveAll(bookings);
//
        List<SystemOption> allOptions = systemOptionService.getAllSystemOption();
        optionMap = new HashMap<>();
        allOptions.forEach(option -> optionMap.put(option.getOptionkey(), option.getOptionValue()));
//
//
        commonDataService.refreshBrands();

//        System.out.println("\n\n===============Database has been initialized with some default data.================\n\n");
    }



}
