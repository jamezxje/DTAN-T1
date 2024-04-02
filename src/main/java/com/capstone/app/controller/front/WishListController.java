package com.capstone.app.controller.front;

import com.capstone.app.entity.Car;
import com.capstone.app.entity.dto.front.response.WishItemResponseDTO;
import com.capstone.app.service.dash.WishItemService;
import com.capstone.app.service.front.FrontCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WishListController {

    private final WishItemService wishItemService;
    private final FrontCarService carService;

    @GetMapping("/wishlist")
    public String wishlist(Model model) {
        List<WishItemResponseDTO> wishItemResponseDTOList = wishItemService.findAllWishItemResponseDTO();
        model.addAttribute("wishlist", wishItemResponseDTOList);
        return "frontend/member/wishlist";
    }

    @PostMapping("/wishlist/{id}")
    public String addToWishlist(RedirectAttributes redirectAttributes, @PathVariable("id") Integer id) {
        Car car = carService.findCarById(id);
        wishItemService.saveWishItem(car);
        redirectAttributes.addFlashAttribute("success", "Add to wishlist successfully");
        return "redirect:/details/{id}";
    }

    @PostMapping({"/wishlist/{carId}/delete", "/wishlist/{carId}/delete/"})
    public String deleteWishItem(RedirectAttributes redirectAttributes, @PathVariable("carId") Integer carId, @RequestParam(name = "page", required = false) String page) {

        Car car = carService.findCarById(carId);

        if (car == null) {
            redirectAttributes.addFlashAttribute("error", "Car not found");
            return "redirect:/wishlist";
        }

        wishItemService.deleteWishItemByMemberIdAndCarId(carId);

        redirectAttributes.addFlashAttribute("message", "Remove from wishlist successfully");

        if (page != null && page.equals("detail")) {
            return "redirect:/details/" + carId;
        }

        return "redirect:/wishlist";
    }
}
