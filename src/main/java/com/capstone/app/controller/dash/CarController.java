package com.capstone.app.controller.dash;

import com.capstone.app.entity.dto.dashboard.request.CarRequestDTO;
import com.capstone.app.entity.dto.filter.CarFilterRequest;
import com.capstone.app.entity.dto.front.response.CarItemResponseDTO;
import com.capstone.app.entity.dto.front.response.RatingDTO;
import com.capstone.app.entity.dto.front.response.RatingDetailResponseDTO;
import com.capstone.app.entity.dto.pagination.PaginationRequest;
import com.capstone.app.entity.dto.pagination.PaginationResponse;
import com.capstone.app.entity.type.FuelType;
import com.capstone.app.entity.type.Status;
import com.capstone.app.entity.type.TransmissionType;
import com.capstone.app.service.dash.BrandService;
import com.capstone.app.service.dash.CarService;
import com.capstone.app.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard/cars")
@PreAuthorize("hasRole('OWNER')")
public class CarController {

    private final CarService carService;
    private final BrandService brandService;

    @GetMapping("/{carId}/feedbacks")
    public String carFeedbacks(Model model, @PathVariable("carId") Integer carId, PaginationRequest request) {
        Integer memberId = RequestUtils.getMemberFromModel(model).getMemberId();
        CarItemResponseDTO carItem = carService.findCarItemByCarId(memberId, carId);

        RatingDTO totalRatingAndAvgRating = carService.findTotalRatingAndAvgRatingByCarId(carId);

        PaginationResponse<List<RatingDetailResponseDTO>> ratingDetailResponseDTOList = carService.findAllRatingByCarId(carId,request);

        model.addAttribute("car", carItem);
        model.addAttribute("filter", request);
        model.addAttribute("ratingInfo", totalRatingAndAvgRating);
        model.addAttribute("ratings", ratingDetailResponseDTOList);

        return "/dashboard/car/feedbacks";
    }

    @GetMapping()
    public String car(Model model, CarFilterRequest request) {
        request.setOwnerId(RequestUtils.getMemberFromModel(model).getMemberId());
        model.addAttribute("status", Status.values());
        model.addAttribute("brands", brandService.findAllBrandItemResponseDTO());
        model.addAttribute("cars", carService.findByFilter(request));
        model.addAttribute("filter", request);
        return "/dashboard/car/list";
    }

    @GetMapping("/add")
    public String addCar(Model model) {
        model.addAttribute("car",
                CarRequestDTO.builder().brandId(1)
                        .carModel("Sedan")
                        .carLicensePlate("123-456")
                        .carColor("Black")
                        .numberOfSeats(4)
                        .productionYear(2021)
                        .transmissionType(TransmissionType.AUTOMATIC)
                        .fuelType(FuelType.PETROL)
                        .mileage(0)
                        .fuelConsumption("10L/100km")
                        .basePrice(100.0)
                        .deposit(100.0)
                        .address("Hanoi")
                        .description("Good car for rent")
                        .additionalFunction("Air conditioner, Radio, GPS, Camera")
                        .termOfUse("You need to carefully").build()
        );

        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("transmissionTypes", TransmissionType.values());
        model.addAttribute("fuelTypes", FuelType.values());
        return "/dashboard/car/form";
    }

    @PostMapping("/save")
    public String createCar(CarRequestDTO car, Model model, RedirectAttributes redirectAttributes) {
        if (car.getCarId() != null) {
            carService.editCar(car);
            redirectAttributes.addFlashAttribute("success", "Car edited successfully");
            return "redirect:/dashboard/cars/" + car.getCarId();
        } else {
            car.setOwnerId(RequestUtils.getMemberFromModel(model).getMemberId());
            carService.addCar(car);
            redirectAttributes.addFlashAttribute("success", "Added car successfully");
            return "redirect:/dashboard/cars/add";
        }

    }

    @GetMapping({"/{carId}/details", "/{carId}", "/{carId}/"})
    public String editCar(Model model, @PathVariable("carId") Integer carId) {

        RatingDTO totalRatingAndAvgRating = carService.findTotalRatingAndAvgRatingByCarId(carId);

        model.addAttribute("ratingInfo", totalRatingAndAvgRating);

        model.addAttribute("JS_FILE", "dashboard/car/form.js");
        model.addAttribute("brands", brandService.getAllBrand());
        model.addAttribute("transmissionTypes", TransmissionType.values());
        model.addAttribute("fuelTypes", FuelType.values());
        model.addAttribute("car", carService.findById(carId));
        return "/dashboard/car/form";
    }

    @GetMapping("/cancel/{carId}")
    public String deleteCarById(@PathVariable("carId") Integer carId, RedirectAttributes redirectAttributes) {
        carService.changeStatus(carId, Status.CANCELLED);
        redirectAttributes.addFlashAttribute("success", "Cancel car successfully");
        return "redirect:/dashboard/cars";
    }
}
