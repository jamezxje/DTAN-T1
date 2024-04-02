package com.capstone.app.entity.dto.filter;

import com.capstone.app.entity.dto.pagination.PaginationRequest;
import com.capstone.app.entity.type.TransmissionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarFilterSearchSortDTO extends PaginationRequest {

	private String keyword;

	private Double minPrice = 0.0;

	private Double maxPrice = 1000.0;

	private Integer brandId;

	private Integer numberOfSeats;

	private TransmissionType transmissionType;

}
