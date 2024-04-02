package com.capstone.app.entity.dto.filter;

import com.capstone.app.entity.type.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class FilterSortDTO {

	private SortDTO[] sorts;

	private Status statusValue;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate returnStartDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate returnEndDate;

	public LocalDateTime getStartDateTime() {
		return returnStartDate != null ? returnStartDate.atStartOfDay() : null;
	}

	public LocalDateTime getEndDateTime() {
		return returnEndDate != null ? returnEndDate.atStartOfDay().plusDays(1) : null;
	}

}
