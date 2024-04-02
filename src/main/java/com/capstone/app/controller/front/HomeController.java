package com.capstone.app.controller.front;


import com.capstone.app.entity.dto.common.PostDTO;
import com.capstone.app.entity.dto.filter.CarFilterRequest;
import com.capstone.app.entity.dto.front.response.BrandDetailResponseDTO;
import com.capstone.app.entity.dto.front.response.CarItemResponseDTO;
import com.capstone.app.entity.dto.pagination.PaginationResponse;
import com.capstone.app.entity.type.PostStatus;
import com.capstone.app.exception.NotFoundException;
import com.capstone.app.service.dash.BrandService;
import com.capstone.app.service.dash.PostService;
import com.capstone.app.service.front.FrontCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class HomeController {
    private final FrontCarService frontCarService;
    private final BrandService brandService;
    private final PostService postService;

    @GetMapping({"/", ""})
    public String homepage(Model model) {
        List<PostDTO> posts = postService.findLatestPublishedPosts(4, PostStatus.PUBLISH);

        model.addAttribute("cars", frontCarService.findAllApprovedCarByAvailableOwner());
        model.addAttribute("popularCars", frontCarService.findAllOrderByBookingRequestCountDesc());
        model.addAttribute("posts", posts);

        return "frontend/home";
    }

    @GetMapping("/brands/{brandId}")
    public String findCarByBrandId(Model model, @PathVariable Integer brandId, CarFilterRequest request) {

        BrandDetailResponseDTO brandDetail = brandService.findBrandDetailResponseDTOById(brandId);

        if (brandDetail == null) {
            throw new NotFoundException();
        }

        request.setBrandId(brandId);

        PaginationResponse<List<CarItemResponseDTO>> paginationResponse = frontCarService.findByBrandAndOwnerStatus(request);

        model.addAttribute("pagination", paginationResponse);
        model.addAttribute("filter", request);
        model.addAttribute("brand", brandDetail);

        return "frontend/car/car-brand";
    }

}