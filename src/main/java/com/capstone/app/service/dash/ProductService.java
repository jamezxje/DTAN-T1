package com.capstone.app.service.dash;

import com.capstone.app.entity.dto.common.ProductItemDTO;
import com.capstone.app.entity.dto.dashboard.request.ProductRequestDTO;
import com.capstone.app.entity.dto.filter.ProductFilterRequest;
import com.capstone.app.entity.dto.front.response.ProductItemResponseDTO;
import com.capstone.app.entity.dto.front.response.RatingDTO;
import com.capstone.app.entity.dto.front.response.RatingDetailResponseDTO;
import com.capstone.app.entity.dto.pagination.PaginationRequest;
import com.capstone.app.entity.dto.pagination.PaginationResponse;
import com.capstone.app.entity.type.Status;

import java.util.List;

public interface ProductService {
    PaginationResponse<List<ProductItemDTO>> findByFilter(ProductFilterRequest request);

    void addProduct(ProductRequestDTO request);

    void editProduct(ProductRequestDTO request);

    ProductRequestDTO findById(Integer id);

    void changeStatus(Integer id, Status status);

//    ProductItemResponseDTO findProductItemByProductId(Integer memberId, Integer productId);
//
//    RatingDTO findTotalRatingAndAvgRatingByProductId(Integer productId);
//
//    PaginationResponse<List<RatingDetailResponseDTO>> findAllRatingByProductId(Integer productId, PaginationRequest request);
}
