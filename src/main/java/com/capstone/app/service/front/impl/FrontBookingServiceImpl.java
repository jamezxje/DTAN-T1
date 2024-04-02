package com.capstone.app.service.front.impl;

import com.capstone.app.config.auth.UserDetailsCustom;
import com.capstone.app.entity.Booking;
import com.capstone.app.entity.Car;
import com.capstone.app.entity.Feedback;
import com.capstone.app.entity.Member;
import com.capstone.app.entity.dto.dashboard.response.MemberResponseDTO;
import com.capstone.app.entity.dto.filter.FilterSortDTO;
import com.capstone.app.entity.dto.front.request.BookingRequestDTO;
import com.capstone.app.entity.dto.front.request.FeedbackResquestDTO;
import com.capstone.app.entity.dto.front.response.BookingDetailResponseDTO;
import com.capstone.app.entity.dto.front.response.BookingItemResponseDTO;
import com.capstone.app.entity.type.PaymentMethod;
import com.capstone.app.entity.type.Status;
import com.capstone.app.mapper.MemberMapper;
import com.capstone.app.repository.BookingRepo;
import com.capstone.app.service.front.FrontBookingService;
import com.capstone.app.util.DateTimeUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FrontBookingServiceImpl implements FrontBookingService {

    private final BookingRepo bookingRepo;
    private final MemberMapper memberMapper;

    @Override
    public boolean addBooking(BookingRequestDTO bookingDTO, Car car, Member member) {

        Booking booking = bookingDTO.toEntity();
        booking.setCar(car);
        booking.setMember(member);
        booking.setCarModel(car.getCarModel());

        bookingRepo.save(booking);
        return booking.getBookingId() != null;
    }

    @Override
    public Page<BookingItemResponseDTO> getAllBookingByMemberId(Integer id, Integer index, Integer size, FilterSortDTO filterSortDTO) {
        Page<BookingItemResponseDTO> results = bookingRepo.findAllBookingByMemberId(id, this.getPaging(index, size), filterSortDTO);
        return results;
    }

    @Override
    public Pageable getPaging(Integer pageNum, Integer pageSize) {
        return PageRequest.of(pageNum, pageSize);
    }

    @Override
    public MemberResponseDTO getMemberFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsCustom userDetail = null;
        if (authentication != null) {
            userDetail = (UserDetailsCustom) authentication.getPrincipal();
        }
        Member member = userDetail.getMember();
        return memberMapper.entityToResponseDTO(member);
    }

    @Override
    public List<Booking> findBookingsByCarAndTime(Status bookingStatus, Status carStatus, Integer carId, LocalDateTime start, LocalDateTime end) {
        return bookingRepo.findBookingsByCarAndTime(bookingStatus, carStatus, carId, start, end);
    }

    @Override
    public boolean isCarAvailable(Integer carId, LocalDateTime start, LocalDateTime end) {
        List<Booking> bookingList = findBookingsByCarAndTime(Status.APPROVED, Status.APPROVED, carId, start, end);
        return bookingList.isEmpty();
    }

    @Override
    public Booking cancelBooking(Booking booking) {
        booking.setStatus(Status.CANCELLED);
        bookingRepo.save(booking);
        return booking;
    }

    @Override
    public boolean isPaymentable(BookingRequestDTO bookingRequestDTO, Double memberWalletBalance) {

        if (bookingRequestDTO.getPaymentMethod().equals(PaymentMethod.PAYMENT_ON_PICKUP)) {
            return true;
        }

        long totalDays = DateTimeUtils.calculateTotalDays(bookingRequestDTO.getPickupTime(), bookingRequestDTO.getReturnTime());
        Double totalPrice = bookingRequestDTO.getBasePrice() * totalDays + bookingRequestDTO.getPickupType().getPickupPrice();

        return totalPrice <= memberWalletBalance;
    }

    @Override
    public BookingDetailResponseDTO getBookingById(Integer id) {

        BookingDetailResponseDTO booking = bookingRepo.findBookingDetailResponseByBookingId(id);

        long diffDays = DateTimeUtils.calculateTotalDays(booking.getPickupTime(), booking.getReturnTime());

        Double total = booking.getBasePrice() * diffDays;

        booking.setSubTotal(total);
        total += booking.getPickupType().getPickupPrice();
        booking.setDays(diffDays);
        booking.setTotal(total);
        return booking;
    }

    @Override
    @Transactional
    public Booking updateBookingInfosByBookingId(Booking booking, BookingRequestDTO dto) {

        booking.setBookingPickupTime(dto.getPickupTime());
        booking.setBookingReturnTime(dto.getReturnTime());
        booking.getBookingDetail().setPickupAddress(dto.getAddress());
        booking.getBookingDetail().setFullName(dto.getFullName());
        booking.getBookingDetail().setPhoneNumber(dto.getPhoneNumber());
        booking.getBookingDetail().setPickupType(dto.getPickupType());
        booking.getBookingDetail().setPaymentMethod(dto.getPaymentMethod());
        bookingRepo.save(booking);
        return booking;
    }

    @Override
    public Booking findBookingByMemberIdAndBookingId(Integer memberId, Integer bookingId) {
        return bookingRepo.findBookingByMember_MemberIdAndBookingId(memberId, bookingId);
    }

    @Override
    public Booking addFeedback(Booking booking, FeedbackResquestDTO feedbackResquestDTO) {
        Feedback feedback = feedbackResquestDTO.toEntity();
        feedback.setFeedbackDate(LocalDateTime.now());
        booking.setFeedback(feedback);
        bookingRepo.save(booking);
        return booking;
    }

    @Override
    public Booking updateFeedback(Booking booking, FeedbackResquestDTO feedbackResquestDTO) {
        booking.getFeedback().setFeedbackUpdatedAt(LocalDateTime.now());
        booking.getFeedback().setFeedbackContent(feedbackResquestDTO.getFeedbackContent());
        booking.getFeedback().setFeedbackPoint(feedbackResquestDTO.getFeedbackPoint());
        bookingRepo.save(booking);
        return booking;
    }

}
