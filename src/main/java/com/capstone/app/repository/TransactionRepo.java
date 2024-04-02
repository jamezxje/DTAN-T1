package com.capstone.app.repository;

import com.capstone.app.entity.Transaction;
import com.capstone.app.entity.dto.filter.TransactionFilterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

    @Query("SELECT d FROM Transaction d JOIN d.member m WHERE " +
            "(:#{#request.memberName} IS NULL OR  " +
            "LOWER(m.fullName) LIKE LOWER(CONCAT('%', :#{#request.memberName}, '%'))) " +
            "AND (:#{#request.status} IS NULL OR d.status = :#{#request.status}) " +
            "AND (:#{#request.type} IS NULL OR d.type = :#{#request.type}) " +
            "AND (:#{#request.memberId} IS NULL OR m.memberId = :#{#request.memberId}) " +
            "AND (:#{#request.toStartLocalDateTime()} IS NULL OR d.requestTime >= :#{#request.toStartLocalDateTime()}) " +
            "AND (:#{#request.toEndLocalDateTime()} IS NULL OR d.requestTime <= :#{#request.toEndLocalDateTime()}) ")
    Page<Transaction> findByFilter(TransactionFilterRequest request, Pageable pageable);
}
