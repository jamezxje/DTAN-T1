package com.capstone.app.repository.specs;

import com.capstone.app.entity.Booking;
import com.capstone.app.entity.type.Status;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class BookingSpecification {

    public static Specification<Booking> isNotDeleted() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isDeleted"), false);
    }

    public static Specification<Booking> hasMemberID(Integer id) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("member").get("memberId"), id);
    }

    public static Specification<Booking> pickUpBetweenDate(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("bookingPickupTime"), startDate, endDate);
    }

    public static Specification<Booking> hasStatus(Status status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Booking> returnBetweenDate(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("bookingReturnTime"), startDate, endDate);
    }


}
