package com.capstone.app.repository;

import com.capstone.app.entity.Car;
import com.capstone.app.entity.dto.common.CarItemDTO;
import com.capstone.app.entity.dto.filter.CarFilterSearchSortDTO;
import com.capstone.app.entity.dto.front.response.CarDetailResponseDTO;
import com.capstone.app.entity.dto.front.response.CarItemResponseDTO;
import com.capstone.app.entity.dto.front.response.RatingDTO;
import com.capstone.app.entity.dto.front.response.RatingDetailResponseDTO;
import com.capstone.app.entity.type.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepo extends JpaRepository<Car, Integer>, JpaSpecificationExecutor<Car> {

    @Query("SELECT new com.capstone.app.entity.dto.front.response.CarDetailResponseDTO(c.carId, c.carModel, c.featureImage.imageUrl, " +
            "c.brand.brandId, c.brand.brandName, c.basePrice, c.carColor, c.fuelType, c.fuelConsumption, " +
            "c.numberOfSeats, c.carLicensePlate, c.productionYear, c.transmissionType, c.mileage, c.deposit, " +
            "c.address, c.description, c.additionalFunction, c.termOfUse) " +
            "FROM Car c WHERE c.carId = :carId")
    CarDetailResponseDTO findCarDetailByCarId(@Param("carId") Integer carId);

    @Query(nativeQuery = true, value = "SELECT " +
            "(SELECT COUNT(*) FROM booking AS b JOIN feedback AS f ON b.feedback_id = f.feedback_id WHERE b.car_id = c.car_id) AS totalRating, " +
            "(SELECT AVG(f.feedback_point) FROM booking AS b JOIN feedback AS f ON b.feedback_id = f.feedback_id WHERE b.car_id = c.car_id) AS averageRating " +
            "FROM car AS c WHERE c.car_id = :carId")
    RatingDTO findTotalRatingAndAvgRatingByCarId(@Param("carId") Integer carId);

    @Query("SELECT new com.capstone.app.entity.dto.front.response.CarItemResponseDTO(c.carId, c.carModel, c.featureImage.imageUrl, c.basePrice, c.carColor, c.fuelConsumption, c.numberOfSeats) FROM Car c JOIN c.owner WHERE c.owner.isActive = true AND c.owner.isVerified = true AND c.status = :Status")
    List<CarItemResponseDTO> findAllApprovedCarByAvailableOwner(@Param("Status") Status Status);

    @Query("SELECT new com.capstone.app.entity.dto.front.response.CarItemResponseDTO( " +
            "c.carId, c.carModel, c.featureImage.imageUrl, c.basePrice, " +
            "c.carColor, c.fuelConsumption, c.numberOfSeats) " +
            "FROM Car c JOIN c.owner " +
            "WHERE c.owner.isActive = true AND c.owner.isVerified = true " +
            "AND c.carId = :carId AND c.owner.memberId = :memberId")
    CarItemResponseDTO findCarItemByMemberIdAndCarId(@Param("memberId") Integer memberId, @Param("carId") Integer carId);

    @Query("SELECT new com.capstone.app.entity.dto.common.CarItemDTO(c.carId,c.carModel,c.brand.brandName,c.featureImage.imageUrl,c.carLicensePlate,c.owner.fullName,c.status,c.productionYear,c.basePrice) FROM Car c " +
            "WHERE (:carModel is null or lower(c.carModel) like lower(concat('%', :carModel,'%'))) " +
            "AND (:brandId is null or c.brand.brandId = :brandId) " +
            "AND (:ownerId is null or c.owner.memberId = :ownerId) " +
            "AND (:status is null or c.status = :status)")
    Page<CarItemDTO> findByFilter(String carModel, Integer brandId, Status status, Integer ownerId, Pageable pageable);


    @Query("SELECT c FROM Car c " +
            "JOIN c.owner o " +
            "WHERE o.isActive = true " +
            "AND o.isVerified = true " +
            "AND c.status = :status " +
            "AND (:brandId is null OR c.brand.brandId = :brandId)")
    Page<Car> findApprovedCarByBrandAndOwnerStatus(@Param("status") Status status, @Param("brandId") Integer brandId, Pageable pageable);

    @Query("SELECT new com.capstone.app.entity.dto.front.response.CarItemResponseDTO( c.carId, c.carModel, c.featureImage.imageUrl, c.basePrice, c.carColor, c.fuelConsumption, c.numberOfSeats ) FROM Car c " +
            "JOIN c.owner o " +
            "WHERE o.isActive = true " +
            "AND o.isVerified = true " +
            "AND c.status = :status " +
            "AND (:brandId is null OR c.brand.brandId = :brandId)")
    Page<CarItemResponseDTO> findApprovedCarItemByBrandAndOwnerStatus(@Param("status") Status status, @Param("brandId") Integer brandId, Pageable pageable);

    @Query("SELECT new com.capstone.app.entity.dto.front.response.CarItemResponseDTO(c.carId, c.carModel, c.featureImage.imageUrl, c.basePrice, c.carColor, c.fuelConsumption, c.numberOfSeats), COUNT(b) AS bookingCount " +
            "FROM Car c " +
            "LEFT JOIN Booking b ON c = b.car " +
            "WHERE c.owner.isActive = true " +
            "AND c.owner.isVerified = true " +
            "AND c.status = :status " +
            "GROUP BY c " +
            "ORDER BY bookingCount DESC")
    List<CarItemResponseDTO> sortCarByNumberOfBookingRequest(@Param("status") Status status);

    @Query("SELECT c.numberOfSeats FROM Car c GROUP BY c.numberOfSeats")
    List<Integer> findAllNumberOfSeats();

    @Query("SELECT new com.capstone.app.entity.dto.front.response.CarItemResponseDTO(c.carId, c.carModel, c.featureImage.imageUrl, c.basePrice, c.carColor, c.fuelConsumption, c.numberOfSeats) FROM Car c " +
            "WHERE (:#{#request.keyword} is null or lower(c.carModel) like lower(concat('%', :#{#request.keyword},'%'))) " +
            "AND (:#{#request.brandId} is null or c.brand.brandId = :#{#request.brandId}) " +
            "AND (:#{#request.minPrice} is null or c.basePrice >= :#{#request.minPrice}) " +
            "AND (:#{#request.maxPrice} is null or c.basePrice <= :#{#request.maxPrice}) " +
            "AND (:#{#request.transmissionType} is null or c.transmissionType = :#{#request.transmissionType}) " +
            "AND (:#{#request.numberOfSeats} is null or c.numberOfSeats = :#{#request.numberOfSeats})")
    Page<CarItemResponseDTO> findCarResponseDTOByFilter(CarFilterSearchSortDTO request, Pageable pageable);

    @Query("SELECT new com.capstone.app.entity.dto.front.response.CarItemResponseDTO(c.carId, c.carModel, c.featureImage.imageUrl, c.basePrice, c.carColor, c.fuelConsumption, c.numberOfSeats) FROM Car c " +
            "INNER JOIN Brand b ON c.brand.brandId = b.brandId " +
            "JOIN c.owner o " +
            "WHERE o.isActive = true " +
            "AND o.isVerified = true " +
            "AND b.brandId = :brandId " +
            "AND c.carId NOT IN (:carId) " +
            "AND c.status = :status")
    Page<CarItemResponseDTO> findRelatedCar(@Param("brandId") Integer brandId, @Param("carId") Integer carId, @Param("status") Status status, Pageable pageable);

    @Query("SELECT new com.capstone.app.entity.dto.front.response.RatingDetailResponseDTO( " +
            "b.feedback.feedbackId, b.feedback.feedbackContent, b.feedback.feedbackPoint, b.feedback.feedbackDate, b.member.userName) " +
            "FROM Booking b " +
            "WHERE b.car.carId = :carId " +
            "AND b.feedback IS NOT NULL " +
            "ORDER BY b.feedback.feedbackDate DESC"
    )
    Page<RatingDetailResponseDTO> findAllRatingDetailByCarId(Integer carId, Pageable pageable);
}
