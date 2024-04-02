package com.capstone.app.service.dash.impl;

import com.capstone.app.entity.Car;
import com.capstone.app.entity.Image;
import com.capstone.app.entity.dto.common.CarItemDTO;
import com.capstone.app.entity.dto.dashboard.request.CarRequestDTO;
import com.capstone.app.entity.dto.filter.CarFilterRequest;
import com.capstone.app.entity.dto.front.response.CarItemResponseDTO;
import com.capstone.app.entity.dto.front.response.RatingDTO;
import com.capstone.app.entity.dto.front.response.RatingDetailResponseDTO;
import com.capstone.app.entity.dto.pagination.PaginationRequest;
import com.capstone.app.entity.dto.pagination.PaginationResponse;
import com.capstone.app.entity.type.Status;
import com.capstone.app.repository.CarRepo;
import com.capstone.app.repository.ImageRepo;
import com.capstone.app.repository.MemberRepo;
import com.capstone.app.service.common.FilesStorageService;
import com.capstone.app.service.dash.BrandService;
import com.capstone.app.service.dash.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepo carRepo;
    private final BrandService brandService;
    private final MemberRepo memberRepo;
    private final ImageRepo imageRepo;
    private final FilesStorageService filesStorageService;

    @Override
    public PaginationResponse<List<CarItemDTO>> findByFilter(CarFilterRequest request) {
        Pageable pageable = PageRequest.of(request.getPageIndex(), request.getPageSize());
        Page<CarItemDTO> cars = carRepo.findByFilter(request.getCarModel(),request.getBrandId(),request.getStatus(),request.getOwnerId(),pageable);
        return new PaginationResponse<>(cars.getContent(), request.getPageIndex() + 1, cars.getTotalPages() + 1, cars.getTotalElements(),request.getPageSize());
    }

    @Override
    public void addCar(CarRequestDTO request) {
        Car car = request.toEntity();
        car.setOwner(memberRepo.findById(request.getOwnerId()).orElseThrow(() -> new RuntimeException("Owner not found")));
        car.setBrand(brandService.findBrandById(request.getBrandId()));
        Image featureImage = filesStorageService.save(request.getFeatureImage());
        car.setFeatureImage(featureImage);
        List<Image> images = filesStorageService.saveAll(request.getImages());
        car.setImages(images);
        carRepo.save(car);
    }

    @Override
    public void editCar(CarRequestDTO request) {
        Car car = carRepo.findById(request.getCarId()).orElseThrow(() -> new RuntimeException("Car not found"));
        request.updateEntity(car);
        car.setBrand(brandService.findBrandById(request.getBrandId()));

        if (request.getFeatureImage() != null && !request.getFeatureImage().isEmpty()) {
            Image featureImage = filesStorageService.save(request.getFeatureImage());
            filesStorageService.delete(car.getFeatureImage());
            car.setFeatureImage(featureImage);
        }

        if (request.getDeleteImagesUrl() != null && !request.getDeleteImagesUrl().isEmpty()) {
            List<Image> deleteImages = imageRepo.findByImageUrl(request.getDeleteImagesUrl());
            filesStorageService.deleteAll(deleteImages);
            car.getImages().removeAll(deleteImages);
        }

        if (request.getImages() != null ) {
            List<Image> images = filesStorageService.saveAll(request.getImages());
            car.getImages().addAll(images);
        }
        carRepo.save(car);
    }

    @Override
    public CarRequestDTO findById(Integer id) {
        Car car = carRepo.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        return CarRequestDTO.toDTO(car);
    }

    @Override
    public void changeStatus(Integer id, Status status) {
        Car car = carRepo.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        car.setStatus(status);
        carRepo.save(car);
    }

    @Override
    public CarItemResponseDTO findCarItemByCarId(Integer memberId, Integer carId ) {
        return carRepo.findCarItemByMemberIdAndCarId(memberId, carId);
    }

    public RatingDTO findTotalRatingAndAvgRatingByCarId(Integer carId) {
        return carRepo.findTotalRatingAndAvgRatingByCarId(carId);
    }

    @Override
    public PaginationResponse<List<RatingDetailResponseDTO>> findAllRatingByCarId(Integer carId, PaginationRequest request) {
        Pageable pageable = PageRequest.of(request.getPageIndex(), request.getPageSize());
        Page<RatingDetailResponseDTO> responseDTOS =  carRepo.findAllRatingDetailByCarId(carId,pageable);
        return new PaginationResponse<>(responseDTOS.getContent(), request.getPageIndex() + 1
                , responseDTOS.getTotalPages() + 1, responseDTOS.getTotalElements(),request.getPageSize());
    }

}
