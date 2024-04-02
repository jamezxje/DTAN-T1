package com.capstone.app.repository;

import com.capstone.app.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepo extends JpaRepository<Image, Integer> {
    @Query("SELECT i FROM Image i WHERE i.imageUrl in :imageUrls")
    List<Image> findByImageUrl(@Param("imageUrls") List<String> imageUrls);

    @Query("SELECT c.images FROM Car c WHERE c.carId = :carId")
    List<Image> findByCarId(Integer carId);
}
