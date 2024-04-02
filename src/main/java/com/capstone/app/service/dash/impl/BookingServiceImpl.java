package com.capstone.app.service.dash.impl;

import com.capstone.app.entity.Booking;
import com.capstone.app.entity.dto.pagination.PaginationResponse;
import com.capstone.app.entity.dto.filter.BookingFilterRequest;
import com.capstone.app.entity.type.PaymentMethod;
import com.capstone.app.entity.type.Status;
import com.capstone.app.repository.BookingRepo;
import com.capstone.app.service.common.WalletService;
import com.capstone.app.service.dash.BookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepo bookingRepo;
    private final WalletService walletService;

    @Override
    public Booking findBookingById(Integer id) {
        return bookingRepo.findById(id).orElse(null);
    }
    @Override
    public PaginationResponse<List<Booking>> findByFilter(BookingFilterRequest request) {
        Pageable pageable = PageRequest.of(request.getPageIndex(), request.getPageSize(),
                Sort.by("bookingRequestTime").descending().and(Sort.by("status")));
        Page<Booking> bookings = bookingRepo.findByBookingId(request, pageable);
        return new PaginationResponse<>(bookings.getContent(), request.getPageIndex() + 1,
                bookings.getTotalPages() + 1, bookings.getTotalElements(), request.getPageSize());
    }

    @Override
    @Transactional
    public String[] changeStatus(Integer id, Status status) {
        Booking booking = bookingRepo.findById(id).orElseThrow();
        booking.setTotalPrice(booking.calculateTotalPrice());

        if (status == Status.DONE) {
            booking.setBookingActualReturnTime(LocalDateTime.now());
            double extraFee = booking.calculateExtraFeePrice();
            if (booking.getCar().getOwner().getWalletBalance() < extraFee){
                return new String[]{"danger", "Owner's wallet balance is not enough"};
            }
            walletService.memberToAdmin(booking.getCar().getOwner(), extraFee, "Extra fee from booking " + booking.getBookingId());
            booking.getBookingDetail().setExtraFee(extraFee);
        }
        else if (status == Status.APPROVED) {
            if (booking.getCar().getOwner().getWalletBalance() < booking.calculateFeePrice()){
                return new String[]{"danger", "Owner's wallet balance is not enough"};
            }
            if (booking.getBookingDetail().getPaymentMethod() == PaymentMethod.PAYMENT_ONLINE){
                if (booking.getMember().getWalletBalance() < booking.getTotalPrice()){
                    return new String[]{"danger", "Member's wallet balance is not enough"};
                }
                walletService.memberToMember(booking.getMember(),booking.getCar().getOwner(), booking.getTotalPrice(), "Payment for booking " + booking.getBookingId());
            }

            List<Booking> currentBooking = bookingRepo.findCarBookingInTime(booking);
            for (Booking b : currentBooking) {
                b.setStatus(Status.CANCELLED_AUTOMATIC);
            }
            bookingRepo.saveAll(currentBooking);
            walletService.memberToAdmin(booking.getCar().getOwner(), booking.calculateFeePrice(), "Fee from booking " + booking.getBookingId());
        }

        booking.setStatus(status);
        bookingRepo.save(booking);
        return new String[]{"success", "Status changed successfully"};
    }

    @Override
    public double calculateTotalIncome(LocalDate start, LocalDate end, Integer ownerId) {
        List<Booking> bookings =
                bookingRepo.findBookingInTimeWithStatus(
                        start == null ? null: start.atStartOfDay(),
                        end == null ? null: end.atStartOfDay().plusDays(1),
                        List.of(Status.DONE,Status.APPROVED),
                        ownerId
                );
        return bookings.stream().mapToDouble(b -> b.calculateFeePrice() + b.calculateExtraFeePrice()).sum();
    }

    @Override
    public double calculateTotalRevenue(LocalDate start, LocalDate end, Integer ownerId) {
        List<Booking> bookings = bookingRepo.findBookingInTimeWithStatus(
                start == null ? null: start.atStartOfDay(),
                end == null ? null: end.atStartOfDay().plusDays(1),
                List.of(Status.APPROVED, Status.DONE),
                ownerId
        );
        return bookings.stream().mapToDouble(Booking::calculateTotalPrice).sum();
    }

    @Override
    public double calculateExpectedRevenue(LocalDate start, LocalDate end, Integer ownerId) {
        List<Booking> bookings = bookingRepo.findBookingInTimeWithStatus(
                start == null ? null: start.atStartOfDay(),
                end == null ? null: end.atStartOfDay().plusDays(1),
                List.of(Status.DONE, Status.APPROVED, Status.PENDING),
                ownerId
        );
        return bookings.stream().mapToDouble(Booking::calculateTotalPrice).sum();
    }

    @Override
    public int countBookingsByStatus(LocalDate start, LocalDate end,Status status, Integer ownerId){
        return bookingRepo.countBookingByStatus(
                start == null ? null: start.atStartOfDay(),
                end == null ? null: end.atStartOfDay().plusDays(1),
                status,
                ownerId
        );
    }
}
