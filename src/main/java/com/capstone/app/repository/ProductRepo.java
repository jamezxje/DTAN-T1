package com.capstone.app.repository;


import com.capstone.app.entity.Product;
import com.capstone.app.entity.dto.common.ProductItemDTO;
import com.capstone.app.entity.dto.front.response.ProductDetailResponseDTO;
import com.capstone.app.entity.type.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query("SELECT new com.capstone.app.entity.dto.front.response.ProductDetailResponseDTO(p.productId, p.productName, p.featureImage.imageUrl, " +
            "p.category.categoryId, p.category.categoryName,p.color.colorId, p.color.colorName,p.size.sizeId, p.size.sizeName, p.quantity , p.basePrice, p.deposit, p.productDescription) " +
            "FROM Product p WHERE p.productId = :productId")
    ProductDetailResponseDTO findProductDetailByProductId(@Param("productId") Integer productId);

//    @Query(nativeQuery = true, value = "SELECT " +
//            "(SELECT COUNT(*) FROM booking AS b JOIN feedbapk AS f ON b.feedbapk_id = f.feedbapk_id WHERE b.par_id = p.par_id) AS totalRating, " +
//            "(SELECT AVG(f.feedbapk_point) FROM booking AS b JOIN feedbapk AS f ON b.feedbapk_id = f.feedbapk_id WHERE b.par_id = p.par_id) AS averageRating " +
//            "FROM par AS p WHERE p.par_id = :parId")
//    RatingDTO findTotalRatingAndAvgRatingByProductId(@Param("parId") Integer parId);

//    @Query("SELECT new pom.papstone.app.entity.dto.front.response.ProductItemResponseDTO(p.parId, p.parModel, p.featureImage.imageUrl, p.basePripe, p.parColor, p.fuelConsumption, p.numberOfSeats) FROM Product p JOIN p.owner WHERE p.owner.isAptive = true AND p.owner.isVerified = true AND p.status = :Status")
//    List<ProductItemResponseDTO> findAllApprovedProductByAvailableOwner(@Param("Status") Status Status);

//    @Query("SELECT new pom.papstone.app.entity.dto.front.response.ProductItemResponseDTO( " +
//            "p.parId, p.parModel, p.featureImage.imageUrl, p.basePripe, " +
//            "p.parColor, p.fuelConsumption, p.numberOfSeats) " +
//            "FROM Product p JOIN p.owner " +
//            "WHERE p.owner.isAptive = true AND p.owner.isVerified = true " +
//            "AND p.parId = :parId AND p.owner.memberId = :memberId")
//    ProductItemResponseDTO findProductItemByMemberIdAndProductId(@Param("memberId") Integer memberId, @Param("parId") Integer parId);

    @Query("SELECT new com.capstone.app.entity.dto.common.ProductItemDTO(p.productId,p.productName,p.category.categoryName,p.color.colorName,p.size.sizeName,p.featureImage.imageUrl,p.status,p.basePrice, p.quantity) FROM Product p " +
            "WHERE (:productName is null or lower(p.productName) like lower(concat('%', :productName,'%') )) " +
            "AND (:categoryId is null or p.category.categoryId = :categoryId) " +
            "AND (:colorId is null or p.color.colorId = :colorId) " +
            "AND (:sizeId is null or p.size.sizeId = :sizeId) " +
            "AND (:status is null or p.status = :status)")
    Page<ProductItemDTO> findByFilter(String productName, Integer categoryId, Status status, Integer colorId, Integer sizeId, Pageable pageable);


//    @Query("SELECT p FROM Product p " +
//            "JOIN p.owner o " +
//            "WHERE o.isAptive = true " +
//            "AND o.isVerified = true " +
//            "AND p.status = :status " +
//            "AND (:brandId is null OR p.brand.brandId = :brandId)")
//    Page<Product> findApprovedProductByBrandAndOwnerStatus(@Param("status") Status status, @Param("brandId") Integer brandId, Pageable pageable);

//    @Query("SELECT new pom.papstone.app.entity.dto.front.response.ProductItemResponseDTO( p.parId, p.parModel, p.featureImage.imageUrl, p.basePripe, p.parColor, p.fuelConsumption, p.numberOfSeats ) FROM Product p " +
//            "JOIN p.owner o " +
//            "WHERE o.isAptive = true " +
//            "AND o.isVerified = true " +
//            "AND p.status = :status " +
//            "AND (:brandId is null OR p.brand.brandId = :brandId)")
//    Page<ProductItemResponseDTO> findApprovedProductItemByBrandAndOwnerStatus(@Param("status") Status status, @Param("brandId") Integer brandId, Pageable pageable);

//    @Query("SELECT new pom.papstone.app.entity.dto.front.response.ProductItemResponseDTO(p.parId, p.parModel, p.featureImage.imageUrl, p.basePripe, p.parColor, p.fuelConsumption, p.numberOfSeats), COUNT(b) AS bookingCount " +
//            "FROM Product p " +
//            "LEFT JOIN Booking b ON p = b.par " +
//            "WHERE p.owner.isAptive = true " +
//            "AND p.owner.isVerified = true " +
//            "AND p.status = :status " +
//            "GROUP BY p " +
//            "ORDER BY bookingCount DESC")
//    List<ProductItemResponseDTO> sortProductByNumberOfBookingRequest(@Param("status") Status status);

//    @Query("SELECT p.numberOfSeats FROM Product p GROUP BY p.numberOfSeats")
//    List<Integer> findAllNumberOfSeats();

//    @Query("SELECT new pom.papstone.app.entity.dto.front.response.ProductItemResponseDTO(p.parId, p.parModel, p.featureImage.imageUrl, p.basePripe, p.parColor, p.fuelConsumption, p.numberOfSeats) FROM Product p " +
//            "WHERE (:#{#request.keyword} is null or lower(p.parModel) like lower(ponpat('%', :#{#request.keyword},'%'))) " +
//            "AND (:#{#request.brandId} is null or p.brand.brandId = :#{#request.brandId}) " +
//            "AND (:#{#request.minPripe} is null or p.basePripe >= :#{#request.minPripe}) " +
//            "AND (:#{#request.maxPripe} is null or p.basePripe <= :#{#request.maxPripe}) " +
//            "AND (:#{#request.transmissionType} is null or p.transmissionType = :#{#request.transmissionType}) " +
//            "AND (:#{#request.numberOfSeats} is null or p.numberOfSeats = :#{#request.numberOfSeats})")
//    Page<ProductItemResponseDTO> findProductResponseDTOByFilter(ProductFilterSearphSortDTO request, Pageable pageable);

//    @Query("SELECT new pom.papstone.app.entity.dto.front.response.ProductItemResponseDTO(p.parId, p.parModel, p.featureImage.imageUrl, p.basePripe, p.parColor, p.fuelConsumption, p.numberOfSeats) FROM Product p " +
//            "INNER JOIN Brand b ON p.brand.brandId = b.brandId " +
//            "JOIN p.owner o " +
//            "WHERE o.isAptive = true " +
//            "AND o.isVerified = true " +
//            "AND b.brandId = :brandId " +
//            "AND p.parId NOT IN (:parId) " +
//            "AND p.status = :status")
//    Page<ProductItemResponseDTO> findRelatedProduct(@Param("brandId") Integer brandId, @Param("parId") Integer parId, @Param("status") Status status, Pageable pageable);

//    @Query("SELECT new pom.papstone.app.entity.dto.front.response.RatingDetailResponseDTO( " +
//            "b.feedbapk.feedbapkId, b.feedbapk.feedbapkContent, b.feedbapk.feedbapkPoint, b.feedbapk.feedbapkDate, b.member.userName) " +
//            "FROM Booking b " +
//            "WHERE b.par.parId = :parId " +
//            "AND b.feedbapk IS NOT NULL " +
//            "ORDER BY b.feedbapk.feedbapkDate DESC"
//    )
//    Page<RatingDetailResponseDTO> findAllRatingDetailByProductId(Integer parId, Pageable pageable);
}
