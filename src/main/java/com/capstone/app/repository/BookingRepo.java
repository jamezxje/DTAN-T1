package com.capstone.app.repository;

import com.capstone.app.entity.Booking;
import com.capstone.app.entity.dto.filter.BookingFilterRequest;
import com.capstone.app.entity.dto.filter.FilterSortDTO;
import com.capstone.app.entity.dto.front.response.BookingDetailResponseDTO;
import com.capstone.app.entity.dto.front.response.BookingItemResponseDTO;
import com.capstone.app.entity.type.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Integer>, JpaSpecificationExecutor<Booking> {

    @Query("SELECT b FROM Booking b "
            + " JOIN b.car c ON c.carId = :carId"
            + " WHERE b.status = :bookingStatus"
            + " AND c.status = :carStatus"
            + " AND b.bookingActualReturnTime IS NULL"
            + " AND ("
            + "    (b.bookingPickupTime <= :start AND b.bookingReturnTime >= :start)"
            + " OR (b.bookingPickupTime <= :end AND b.bookingReturnTime >= :end)"
            + " OR (b.bookingPickupTime >= :start AND b.bookingReturnTime <= :end)"
            + " )"
    )
    List<Booking> findBookingsByCarAndTime(@Param("bookingStatus") Status bookingStatus,
                                           @Param("carStatus") Status carStatus,
                                           @Param("carId") Integer carId,
                                           @Param("start") LocalDateTime start,
                                           @Param("end") LocalDateTime end);

    @Query("SELECT b FROM Booking b "
            + " JOIN b.car c ON c.carId = :carId"
            + " AND b.status = :bookingStatus"
            + " AND c.status = :carStatus"
            + " AND b.bookingActualReturnTime IS NULL"
            + " AND :currentTime BETWEEN b.bookingPickupTime AND b.bookingReturnTime"
    )
    List<Booking> findBookingsByCarAndTime(@Param("bookingStatus") Status bookingStatus,
                                           @Param("carStatus") Status carStatus,
                                           @Param("carId") Integer carId,
                                           @Param("currentTime") LocalDateTime currentTime);

    @Query("SELECT b FROM Booking b WHERE " +
            "(:#{#request.carOwnerId} IS NULL OR b.car.owner.memberId = :#{#request.carOwnerId}) AND " +
            "(:#{#request.carModel} IS NULL OR lower(b.carModel) like lower(concat('%', :#{#request.carModel},'%'))) AND " +
            "(:#{#request.pickupDate} IS NULL OR b.bookingPickupTime >= :#{#request.toStartLocalDate()}) AND " +
            "(:#{#request.returnDate} IS NULL OR b.bookingReturnTime <= :#{#request.toEndLocalDate()}) AND " +
            "(:#{#request.status} IS NULL OR b.status = :#{#request.status})")
    Page<Booking> findByBookingId(BookingFilterRequest request, Pageable pageable);


    @Query("SELECT b FROM Booking b WHERE b.car.carId = :#{#booking.car.carId} AND " +
            "b.status = com.capstone.app.entity.type.Status.PENDING AND " +
            "b.bookingId != :#{#booking.bookingId} AND " +
            "((:#{#booking.bookingPickupTime} >= b.bookingPickupTime AND :#{#booking.bookingPickupTime} <= b.bookingReturnTime) OR " +
            "(:#{#booking.bookingReturnTime} >=  b.bookingPickupTime AND :#{#booking.bookingReturnTime} <= b.bookingReturnTime))")
    List<Booking> findCarBookingInTime(Booking booking);

    @Query("SELECT new com.capstone.app.entity.dto.front.response.BookingDetailResponseDTO(" +
            "b.bookingId, i.imageUrl, " +
            "b.carModel, c.deposit, c.basePrice, " +
            "b.bookingPickupTime, b.bookingReturnTime, b.bookingActualReturnTime, " +
            "bd.pickupAddress, bd.fullName, bd.phoneNumber, " +
            "bd.pickupType, bd.paymentMethod, b.status, b.totalPrice, " +
            "fb.feedbackId, fb.feedbackContent, fb.feedbackPoint, fb.feedbackDate, fb.feedbackUpdatedAt) " +
            "FROM Booking b " +
            "JOIN b.bookingDetail bd JOIN b.car c JOIN b.car.featureImage i " +
            "LEFT JOIN Feedback fb ON b.feedback.feedbackId = fb.feedbackId " +
            "WHERE b.bookingId = :bookingId")
    BookingDetailResponseDTO findBookingDetailResponseByBookingId(@Param("bookingId") Integer bookingId);

    @Query("SELECT b FROM Booking b WHERE " +
            "(:start is null OR b.bookingRequestTime >= :start) AND " +
            "(:end is null OR b.bookingRequestTime <= :end) AND " +
            "(:status is null OR b.status in :status) AND" +
            "(:ownerId is null OR b.car.owner.memberId = :ownerId)"
    )
    List<Booking> findBookingInTimeWithStatus(@Param("start") LocalDateTime start
            , @Param("end") LocalDateTime end
            , @Param("status") List<Status> status,
                                              @Param("ownerId") Integer ownerId);

    @Query("SELECT count(b) FROM Booking b  WHERE " +
            "(:start is null OR b.bookingRequestTime >= :start) AND " +
            "(:end is null OR b.bookingRequestTime <= :end) AND " +
            "(:status is null OR b.status = :status) AND" +
            "(:ownerId is null OR b.car.owner.memberId = :ownerId)"
    )
    int countBookingByStatus(@Param("start") LocalDateTime start
            , @Param("end") LocalDateTime end,
                             @Param("status") Status status,
                             @Param("ownerId") Integer ownerId);

    Booking findBookingByMember_MemberIdAndBookingId(Integer memberId, Integer bookingId);

    @Query("SELECT new com.capstone.app.entity.dto.front.response.BookingItemResponseDTO(b.bookingId, b.carModel, i.imageUrl, b.bookingPickupTime, b.bookingReturnTime, b.totalPrice, b.status) " +
            "FROM Booking b JOIN b.member m " +
            "JOIN b.car.featureImage i " +
            "WHERE m.memberId IS NULL OR m.memberId = :memberId " +
            "AND b.isDeleted = false " +
            "AND (:#{#filter.returnStartDate} IS NULL OR b.bookingReturnTime >= :#{#filter.getStartDateTime()}) " +
            "AND (:#{#filter.returnEndDate} IS NULL OR b.bookingReturnTime <= :#{#filter.getEndDateTime()}) " +
            "AND (:#{#filter.statusValue} IS NULL OR b.status = :#{#filter.statusValue})")
    Page<BookingItemResponseDTO> findAllBookingByMemberId(@Param("memberId") Integer memberId, Pageable pageable, FilterSortDTO filter);
}
