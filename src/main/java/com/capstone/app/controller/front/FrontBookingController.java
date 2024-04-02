package com.capstone.app.controller.front;

import com.capstone.app.config.auth.UserDetailsCustom;
import com.capstone.app.entity.Booking;
import com.capstone.app.entity.Car;
import com.capstone.app.entity.Member;
import com.capstone.app.entity.dto.dashboard.response.MemberResponseDTO;
import com.capstone.app.entity.dto.filter.FilterSortDTO;
import com.capstone.app.entity.dto.front.request.BookingRequestDTO;
import com.capstone.app.entity.dto.front.request.FeedbackResquestDTO;
import com.capstone.app.entity.dto.front.response.BookingDetailResponseDTO;
import com.capstone.app.entity.dto.front.response.BookingItemResponseDTO;
import com.capstone.app.entity.type.PaymentMethod;
import com.capstone.app.entity.type.PickupType;
import com.capstone.app.entity.type.Status;
import com.capstone.app.exception.NotFoundException;
import com.capstone.app.repository.MemberRepo;
import com.capstone.app.service.front.FrontBookingService;
import com.capstone.app.service.front.FrontCarService;
import com.capstone.app.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasRole('CUSTOMER')")
public class FrontBookingController {

    private final FrontCarService carService;
    private final FrontBookingService bookingService;
    private final MemberRepo memberRepo;

    @GetMapping("/details/{carId}/booking")
    public String showBookingPage(@PathVariable("carId") Integer carId, ModelMap map, RedirectAttributes redirectAttributes) {

        Car car = carService.findCarById(carId);

        if (!carService.isCarAvailableToBooking(carId)) {
            redirectAttributes.addFlashAttribute("error", "Car is not available for booking!");
            return "redirect:/details/" + carId;
        }

        List<PaymentMethod> paymentMethods = Arrays.asList(PaymentMethod.values());
        List<PickupType> pickupTypes = Arrays.asList(PickupType.values());

        map.addAttribute("car", car);
        map.addAttribute("paymentMethods", paymentMethods);
        map.addAttribute("pickupTypes", pickupTypes);

        return "frontend/car/car-booking";
    }

    @PostMapping("/details/{carId}/booking")
    public String processBooking(@PathVariable("carId") Integer carId,
                                 RedirectAttributes redirectAttributes,
                                 BookingRequestDTO bookingRequestDTO, Model model) {

        Member member = memberRepo.findById(RequestUtils
                                .getMemberFromModel(model)
                                .getMemberId())
                                .orElseThrow(NotFoundException::new);

        Car car = carService.findCarById(carId);

        if (car == null) {
            throw new NotFoundException();
        }

        if (!carService.isCarAvailableToBooking(carId, bookingRequestDTO.getPickupTime(), bookingRequestDTO.getReturnTime())) {
            redirectAttributes.addFlashAttribute("error", "Car is not available for booking!");
        } else if (!bookingService.isPaymentable(bookingRequestDTO, member.getWalletBalance())) {
            redirectAttributes.addFlashAttribute("error", "Not enough money in your wallet!");
        } else {
            bookingService.addBooking(bookingRequestDTO, car, member);
            redirectAttributes.addFlashAttribute("success", "Booking request has been sent, please wait the car's owner review your booking!");
        }

        return "redirect:/details/" + carId;
    }


    @GetMapping({"/history"})
    public String showBookingHistoryPage(
            @ModelAttribute(value = "filterSortDTO") FilterSortDTO filterSortDTO,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNum,
            Model model) {
        MemberResponseDTO memberResponseDTO = bookingService.getMemberFromContext();
        int numOfItems = 10;
        Page<BookingItemResponseDTO> page = bookingService.getAllBookingByMemberId(memberResponseDTO.getMemberId(), pageNum - 1, numOfItems, filterSortDTO);
        model.addAttribute("filterSortDTO", filterSortDTO);
        model.addAttribute("statuses", Status.values());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("bookings", page);

        return "frontend/booking/booking-history-list";
    }

    @GetMapping({"history/booking/{bookingId}"})
    public String showBookingDetails(@PathVariable("bookingId") Integer bookingId, Model model) {

        BookingDetailResponseDTO booking = bookingService.getBookingById(bookingId);

        if (booking.getBookingId() == null) {
            throw new NotFoundException();
        }

        model.addAttribute("booking", booking);

        if (booking.getStatus() == Status.PENDING) {
            model.addAttribute("pickupTypes", PickupType.values());
            model.addAttribute("paymentMethods", PaymentMethod.values());
        }

        return "frontend/booking/booking-history-details";
    }

    @PostMapping("/history/booking/{bookingId}/cancel")
    public String customerCancelBooking(@PathVariable("bookingId") Integer bookingId, RedirectAttributes redirectAttributes, Model model) {

        Member member = memberRepo.findById(RequestUtils
                        .getMemberFromModel(model)
                        .getMemberId())
                .orElseThrow(NotFoundException::new);

        Booking booking = bookingService.findBookingByMemberIdAndBookingId(member.getMemberId(), bookingId);

        if (booking == null) {
            redirectAttributes.addFlashAttribute("error", "Booking not found!");
            return "redirect:/history";
        }

        Booking savedBooking = bookingService.cancelBooking(booking);

        if (savedBooking.getStatus() == Status.CANCELLED) {
            redirectAttributes.addFlashAttribute("success", "Booking cancelled successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Booking not cancelled!");
        }
        return "redirect:/history";
    }

    @PostMapping("/history/booking/{bookingId}/update")
    public String updateInfosForPendingBooking(@PathVariable(value = "bookingId") Integer bookingId,
                                               BookingRequestDTO bookingRequestDTO, RedirectAttributes redirectAttributes, Model model) {

        Member member = memberRepo.findById(RequestUtils
                        .getMemberFromModel(model)
                        .getMemberId())
                .orElseThrow(NotFoundException::new);

        if (!bookingService.isPaymentable(bookingRequestDTO, member.getWalletBalance())) {
            redirectAttributes.addFlashAttribute("error", "Not enough money in your wallet!");
            return "redirect:/history/booking/" + bookingId;
        }

        Booking booking = bookingService.findBookingByMemberIdAndBookingId(member.getMemberId(), bookingId);

        if (booking == null) {
            redirectAttributes.addFlashAttribute("error", "Booking not found!");
            return "redirect:/history";
        }

        bookingService.updateBookingInfosByBookingId(booking, bookingRequestDTO);

        redirectAttributes.addFlashAttribute("success", "Booking updated successfully!");

        return "redirect:/history/booking/" + bookingId;
    }

    @PostMapping("/history/booking/{bookingId}/feedback")

    public String addFeedback(@PathVariable("bookingId") Integer bookingId, FeedbackResquestDTO feedbackResquestDTO, RedirectAttributes redirectAttributes) {

        if (feedbackResquestDTO.getFeedbackPoint() == null || feedbackResquestDTO.getFeedbackPoint() < 1 || feedbackResquestDTO.getFeedbackPoint() > 5 || feedbackResquestDTO.getFeedbackContent().isBlank()) {
            redirectAttributes.addFlashAttribute("error", "Feedback point and content are required! Point must be between 1 and 5!");
            return "redirect:/history/booking/" + bookingId;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsCustom userDetailsCustom = (UserDetailsCustom) authentication.getPrincipal();
        Member customer = userDetailsCustom.getMember();

        Booking booking = bookingService.findBookingByMemberIdAndBookingId(customer.getMemberId(), bookingId);
        if (booking == null) {
            redirectAttributes.addFlashAttribute("error", "Booking not found!");
            return "redirect:/history/booking/" + bookingId;
        } else if (booking.getStatus() != Status.DONE) {
            redirectAttributes.addFlashAttribute("error", "Cant add feedback into un-done booking!");
            return "redirect:/history/booking/" + bookingId;
        } else if (feedbackResquestDTO.getFeedbackId() != null) {

            if (booking.getFeedback().getFeedbackUpdatedAt() != null) {
                redirectAttributes.addFlashAttribute("error", "Feedback only can be updated once!");
                return "redirect:/history/booking/" + bookingId;
            }

            bookingService.updateFeedback(booking, feedbackResquestDTO);

            redirectAttributes.addFlashAttribute("success", "Feedback updated successfully!");

            return "redirect:/history/booking/" + bookingId;
        }

        Booking result = bookingService.addFeedback(booking, feedbackResquestDTO);

        if (result.getFeedback() == null) {
            redirectAttributes.addFlashAttribute("error", "Something went wrong!");
            return "redirect:/history/booking/" + bookingId;
        }

        redirectAttributes.addFlashAttribute("success", "Feedback added successfully!");
        return "redirect:/history/booking/" + bookingId;
    }

}
