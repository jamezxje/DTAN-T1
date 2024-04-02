package com.capstone.app.service.front.impl;

import com.capstone.app.entity.*;
import com.capstone.app.entity.dto.filter.CarFilterRequest;
import com.capstone.app.entity.dto.filter.CarFilterSearchSortDTO;
import com.capstone.app.entity.dto.front.response.CarDetailResponseDTO;
import com.capstone.app.entity.dto.front.response.CarItemResponseDTO;
import com.capstone.app.entity.dto.front.response.RatingDTO;
import com.capstone.app.entity.dto.front.response.RatingDetailResponseDTO;
import com.capstone.app.entity.dto.pagination.PaginationRequest;
import com.capstone.app.entity.dto.pagination.PaginationResponse;
import com.capstone.app.entity.type.Status;
import com.capstone.app.exception.NotFoundException;
import com.capstone.app.repository.BookingRepo;
import com.capstone.app.repository.CarRepo;
import com.capstone.app.repository.ImageRepo;
import com.capstone.app.repository.WishItemRepo;
import com.capstone.app.service.front.FrontCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FrontCarServiceImpl implements FrontCarService {

    private final CarRepo carRepo;
    private final BookingRepo bookingRepo;
    private final ImageRepo imageRepo;
    private final WishItemRepo wishItemRepo;

    @Override
    public PaginationResponse<List<RatingDetailResponseDTO>> findAllRatingDetailByCarId(Integer carId, PaginationRequest request) {
        Pageable pageable = PageRequest.of(request.getPageIndex(), request.getPageSize());
        Page<RatingDetailResponseDTO> responseDTOPage = carRepo.findAllRatingDetailByCarId(carId, pageable);
        return new PaginationResponse<>(responseDTOPage.getContent(), request.getPageIndex() + 1, responseDTOPage.getTotalPages() + 1,
                responseDTOPage.getTotalElements(), request.getPageSize());
    }

    @Override
    public Car findCarById(Integer id) {
        return carRepo.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public CarDetailResponseDTO findCarDetailByCarId(Member member, Integer carId) {

        CarDetailResponseDTO carDetail = carRepo.findCarDetailByCarId(carId);

        if (carDetail == null) {
            throw new NotFoundException();
        }

        if (member != null) {
            WishItemId wishItemId = new WishItemId(member.getMemberId(), carId);
            boolean isInWishlist = wishItemRepo.existsWishItemByWishItemId(wishItemId);
            carDetail.setInWishlist(isInWishlist);
        }

        boolean isCarAvailableToBooking = isCarAvailableToBooking(carId);
        carDetail.setCarAvailableToBooking(isCarAvailableToBooking);

        List<Image> images = imageRepo.findByCarId(carId);
        List<String> imageUrls = images.stream().map(Image::getImageUrl).collect(Collectors.toList());
        carDetail.setImages(imageUrls);

        return carDetail;
    }

    @Override
    public PaginationResponse<List<CarItemResponseDTO>> findCarDtoByFilter(CarFilterSearchSortDTO request) {
        Pageable pageable = PageRequest.of(request.getPageIndex(), request.getPageSize());
        Page<CarItemResponseDTO> carResponseDTOPage = carRepo.findCarResponseDTOByFilter(request, pageable);
        return new PaginationResponse<>(carResponseDTOPage.getContent(), request.getPageIndex() + 1, carResponseDTOPage.getTotalPages() + 1,
                carResponseDTOPage.getTotalElements(), request.getPageSize());
    }

    @Override
    public List<CarItemResponseDTO> findAllApprovedCarByAvailableOwner() {
        Status Status = com.capstone.app.entity.type.Status.APPROVED;
        return carRepo.findAllApprovedCarByAvailableOwner(Status);
    }

    @Override
    public List<Integer> getAllNumberOfSeats() {
        return carRepo.findAllNumberOfSeats();
    }


    @Override
    public boolean isCarAvailableToBooking(Integer carId, LocalDateTime start, LocalDateTime end) {

        if (carId == null || start == null || end == null) {
            return false;
        }

        Car car = carRepo.findById(carId).orElse(null);

        if (car == null) {
            return false;
        }

        List<Booking> bookings = bookingRepo.findBookingsByCarAndTime(Status.APPROVED, Status.APPROVED, car.getCarId(), start, end);
        return bookings.isEmpty();
    }

    @Override
    public boolean isCarAvailableToBooking(Integer carId) {
        List<Booking> bookings = bookingRepo.findBookingsByCarAndTime(Status.APPROVED, Status.APPROVED, carId, LocalDateTime.now());
        return bookings.isEmpty();
    }

    @Override
    public PaginationResponse<List<CarItemResponseDTO>> findByBrandAndOwnerStatus(CarFilterRequest request) {
        Status Status = com.capstone.app.entity.type.Status.APPROVED;
        Pageable pageable = PageRequest.of(request.getPageIndex(), request.getPageSize());
        Page<CarItemResponseDTO> cars = carRepo.findApprovedCarItemByBrandAndOwnerStatus(Status, request.getBrandId(), pageable);
        return new PaginationResponse<>(cars.getContent(), request.getPageIndex() + 1, cars.getTotalPages() + 1, cars.getTotalElements(), request.getPageSize());
    }

    @Override
    public List<CarItemResponseDTO> findAllOrderByBookingRequestCountDesc() {
        Status Status = com.capstone.app.entity.type.Status.APPROVED;
        return carRepo.sortCarByNumberOfBookingRequest(Status);
    }

    @Override
    public Page<CarItemResponseDTO> findRelatedCar(Integer brandId, Integer carId) {
        Status Status = com.capstone.app.entity.type.Status.APPROVED;
        return carRepo.findRelatedCar(brandId, carId, Status, PageRequest.ofSize(8));
    }

    @Override
    public RatingDTO findTotalRatingAndAvgRatingByCarId(Integer carId) {
        return carRepo.findTotalRatingAndAvgRatingByCarId(carId);
    }
}
