package com.capstone.app.repository;

import com.capstone.app.entity.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDetailRepo extends JpaRepository<BookingDetail, Integer> {
}
