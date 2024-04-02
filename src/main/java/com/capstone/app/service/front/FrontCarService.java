package com.capstone.app.service.front;

import com.capstone.app.entity.Car;
import com.capstone.app.entity.Member;
import com.capstone.app.entity.dto.filter.CarFilterRequest;
import com.capstone.app.entity.dto.filter.CarFilterSearchSortDTO;
import com.capstone.app.entity.dto.front.response.CarDetailResponseDTO;
import com.capstone.app.entity.dto.front.response.CarItemResponseDTO;
import com.capstone.app.entity.dto.front.response.RatingDTO;
import com.capstone.app.entity.dto.front.response.RatingDetailResponseDTO;
import com.capstone.app.entity.dto.pagination.PaginationRequest;
import com.capstone.app.entity.dto.pagination.PaginationResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface FrontCarService {
    PaginationResponse<List<RatingDetailResponseDTO>> findAllRatingDetailByCarId(Integer carId, PaginationRequest request);

    Car findCarById(Integer carId);

    CarDetailResponseDTO findCarDetailByCarId(Member member, Integer carId);

    PaginationResponse<List<CarItemResponseDTO>> findCarDtoByFilter(CarFilterSearchSortDTO request);

    List<CarItemResponseDTO> findAllApprovedCarByAvailableOwner();

    List<Integer> getAllNumberOfSeats();

    boolean isCarAvailableToBooking(Integer carId, LocalDateTime start, LocalDateTime end);

    boolean isCarAvailableToBooking(Integer carId);

    PaginationResponse<List<CarItemResponseDTO>> findByBrandAndOwnerStatus(CarFilterRequest request);

    List<CarItemResponseDTO> findAllOrderByBookingRequestCountDesc();

    Page<CarItemResponseDTO> findRelatedCar(Integer brandId, Integer carId);

    RatingDTO findTotalRatingAndAvgRatingByCarId(Integer carId);
}
