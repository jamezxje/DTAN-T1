package com.capstone.app.service.dash;

import com.capstone.app.entity.dto.common.CarItemDTO;
import com.capstone.app.entity.dto.dashboard.request.CarRequestDTO;
import com.capstone.app.entity.dto.filter.CarFilterRequest;
import com.capstone.app.entity.dto.front.response.CarItemResponseDTO;
import com.capstone.app.entity.dto.front.response.RatingDTO;
import com.capstone.app.entity.dto.front.response.RatingDetailResponseDTO;
import com.capstone.app.entity.dto.pagination.PaginationRequest;
import com.capstone.app.entity.dto.pagination.PaginationResponse;
import com.capstone.app.entity.type.Status;

import java.util.List;

public interface CarService {
    PaginationResponse<List<CarItemDTO>> findByFilter(CarFilterRequest request);
    void addCar(CarRequestDTO request);
    void editCar(CarRequestDTO request);
    CarRequestDTO findById(Integer id);
    void changeStatus(Integer id, Status status);
    CarItemResponseDTO findCarItemByCarId(Integer memberId,Integer carId);
    RatingDTO findTotalRatingAndAvgRatingByCarId(Integer carId);
    PaginationResponse<List<RatingDetailResponseDTO>> findAllRatingByCarId(Integer carId, PaginationRequest request);
}
