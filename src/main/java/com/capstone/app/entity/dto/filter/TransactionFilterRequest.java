package com.capstone.app.entity.dto.filter;

import com.capstone.app.entity.dto.pagination.PaginationRequest;
import com.capstone.app.entity.type.TransactionStatus;
import com.capstone.app.entity.type.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionFilterRequest extends PaginationRequest {
    private String memberName;
    private Integer memberId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private TransactionType type;
    private TransactionStatus status;

    public LocalDateTime toStartLocalDateTime() {
        return startDate != null ? startDate.atStartOfDay() : null;
    }

    public LocalDateTime toEndLocalDateTime() {
        return endDate != null ? endDate.atStartOfDay() : null;
    }
}
