package com.capstone.app.service.front;

import com.capstone.app.entity.Booking;
import com.capstone.app.entity.Car;
import com.capstone.app.entity.Member;
import com.capstone.app.entity.dto.dashboard.response.MemberResponseDTO;
import com.capstone.app.entity.dto.filter.FilterSortDTO;
import com.capstone.app.entity.dto.front.request.BookingRequestDTO;
import com.capstone.app.entity.dto.front.request.FeedbackResquestDTO;
import com.capstone.app.entity.dto.front.response.BookingDetailResponseDTO;
import com.capstone.app.entity.dto.front.response.BookingItemResponseDTO;
import com.capstone.app.entity.type.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface FrontBookingService {

	boolean addBooking(BookingRequestDTO bookingDTO, Car car, Member member);

	Page<BookingItemResponseDTO> getAllBookingByMemberId(Integer id, Integer index, Integer size, FilterSortDTO filterSortDTO);

	Pageable getPaging(Integer pageNum, Integer pageSize);

	MemberResponseDTO getMemberFromContext();

	List<Booking> findBookingsByCarAndTime(Status bookingStatus, Status carStatus, Integer carId, LocalDateTime start, LocalDateTime end);

	boolean isCarAvailable(Integer carId, LocalDateTime start, LocalDateTime end);

	Booking cancelBooking(Booking booking);

	boolean isPaymentable(BookingRequestDTO bookingRequestDTO, Double memberWalletBalance);

	BookingDetailResponseDTO getBookingById(Integer id);

	Booking updateBookingInfosByBookingId(Booking booking, BookingRequestDTO dto);

	Booking findBookingByMemberIdAndBookingId(Integer memberId, Integer bookingId);

	Booking addFeedback(Booking booking, FeedbackResquestDTO feedbackResquestDTO);

	Booking updateFeedback(Booking booking, FeedbackResquestDTO feedbackResquestDTO);

}
