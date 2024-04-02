package com.capstone.app.controller.front;

import com.capstone.app.entity.Member;
import com.capstone.app.entity.dto.front.response.CarDetailResponseDTO;
import com.capstone.app.entity.dto.front.response.RatingDTO;
import com.capstone.app.entity.dto.front.response.RatingDetailResponseDTO;
import com.capstone.app.entity.dto.pagination.PaginationRequest;
import com.capstone.app.entity.dto.pagination.PaginationResponse;
import com.capstone.app.service.front.FrontCarService;
import com.capstone.app.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FrontCarController {

    private final FrontCarService carService;

    @GetMapping("/details/{carId}")
    public String carDetail(@PathVariable("carId") Integer carId, PaginationRequest request, ModelMap map, Model model) {
        Member memberId = RequestUtils.getMemberFromModel(model);
        CarDetailResponseDTO car = carService.findCarDetailByCarId(memberId,carId);

        RatingDTO totalRatingAndAvgRating = carService.findTotalRatingAndAvgRatingByCarId(carId);
        car.setTotalRating(totalRatingAndAvgRating.getTotalRating());
        car.setAverageRating(totalRatingAndAvgRating.getAverageRating());

        PaginationResponse<List<RatingDetailResponseDTO>> ratingDetailResponseDTOList = carService.findAllRatingDetailByCarId(carId, request);

        map.addAttribute("car", car);
        map.addAttribute("ratings", ratingDetailResponseDTOList);
        map.addAttribute("filter", request);
        model.addAttribute("relatedCars", carService.findRelatedCar(car.getBrandId(), carId));

        return "frontend/car/car-details";
    }
}
