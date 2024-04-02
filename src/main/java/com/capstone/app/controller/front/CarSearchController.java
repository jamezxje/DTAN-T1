package com.capstone.app.controller.front;

import com.capstone.app.entity.dto.filter.CarFilterSearchSortDTO;
import com.capstone.app.entity.dto.front.response.CarItemResponseDTO;
import com.capstone.app.entity.dto.pagination.PaginationResponse;
import com.capstone.app.entity.type.TransmissionType;
import com.capstone.app.service.front.FrontCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CarSearchController {

    private final FrontCarService frontCarService;

    @GetMapping(value = "/search")
    public String searchFilterCar(CarFilterSearchSortDTO request, Model model) {
        PaginationResponse<List<CarItemResponseDTO>> page = frontCarService.findCarDtoByFilter(request);
        model.addAttribute("search", request);
        model.addAttribute("transmissionTypes", TransmissionType.values());
        model.addAttribute("seats", frontCarService.getAllNumberOfSeats());
        model.addAttribute("cars", page);
        return "frontend/search";
    }
}
