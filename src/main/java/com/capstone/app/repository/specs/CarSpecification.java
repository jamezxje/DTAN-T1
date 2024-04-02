package com.capstone.app.repository.specs;

import com.capstone.app.entity.Car;
import com.capstone.app.entity.type.Status;
import com.capstone.app.entity.type.TransmissionType;
import org.springframework.data.jpa.domain.Specification;

public class CarSpecification {

    public static Specification<Car> isCarNotDeleted() {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("isDeleted"), false);
    }

    public static Specification<Car> isCarAvailable() {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("status"), Status.APPROVED);
    }

    public static Specification<Car> hasOwnerIsActived() {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("owner").get("isActive"), true);
    }

    public static Specification<Car> hasOwnerIsVerified() {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("owner").get("isVerified"), true);
    }

    public static Specification<Car> hasPriceBetweenRange(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.between(root.get("basePrice"), minPrice, maxPrice);
    }

    public static Specification<Car> hasModel(String model) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get("carModel"), "%" + model + "%");
    }

    public static Specification<Car> isBrand(Integer brandId) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("brand").get("brandId"), brandId);
    }

    public static Specification<Car> hasNumberOfSeats(Integer seats) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("numberOfSeats"), seats);
    }

    public static Specification<Car> hasTransmissionType(String transmissonType) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("transmissionType"), TransmissionType.valueOf(transmissonType));
    }

}
