package com.capstone.app.controller.dash;

import com.capstone.app.entity.Booking;
import com.capstone.app.entity.dto.filter.BookingFilterRequest;
import com.capstone.app.entity.type.Status;
import com.capstone.app.service.dash.BookingService;
import com.capstone.app.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard/bookings")
@PreAuthorize("hasRole('OWNER')")
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/request")
    public String bookingRequestList(Model model, BookingFilterRequest request) {
        request.setCarOwnerId(RequestUtils.getMemberFromModel(model).getMemberId());
        model.addAttribute("status", Status.values());
        model.addAttribute("bookings", bookingService.findByFilter(request));
        model.addAttribute("filter", request);
        return "/dashboard/booking/request";
    }


    @GetMapping({"/request/{bookingId}", "/request/{bookingId}/"})
    public String bookingDetail(Model model, @PathVariable("bookingId") Integer bookingId) {

        Booking booking = bookingService.findBookingById(bookingId);

        model.addAttribute("booking", booking);

        return "/dashboard/booking/detail";
    }

    @GetMapping("/request/status")
    public String changeBookingRequestStatus(@RequestParam("id") Integer id, @RequestParam("status") Status status,
                                             RedirectAttributes attributes) {
        attributes.addFlashAttribute("message", bookingService.changeStatus(id, status));
        return "redirect:/dashboard/bookings/request";
    }
}
