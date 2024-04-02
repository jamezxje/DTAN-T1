package com.capstone.app.service.dash.impl;

import com.capstone.app.entity.Product;
import com.capstone.app.entity.Image;
import com.capstone.app.entity.dto.common.ProductItemDTO;
import com.capstone.app.entity.dto.dashboard.request.ProductRequestDTO;
import com.capstone.app.entity.dto.filter.ProductFilterRequest;
import com.capstone.app.entity.dto.front.response.ProductItemResponseDTO;
import com.capstone.app.entity.dto.front.response.RatingDTO;
import com.capstone.app.entity.dto.front.response.RatingDetailResponseDTO;
import com.capstone.app.entity.dto.pagination.PaginationRequest;
import com.capstone.app.entity.dto.pagination.PaginationResponse;
import com.capstone.app.entity.type.Status;
import com.capstone.app.repository.ProductRepo;
import com.capstone.app.repository.ImageRepo;
import com.capstone.app.repository.MemberRepo;
import com.capstone.app.service.common.FilesStorageService;
import com.capstone.app.service.dash.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ColorService colorService;
    private final SizeService sizeService;
    private final MemberRepo memberRepo;
    private final ImageRepo imageRepo;
    private final FilesStorageService filesStorageService;

    @Override
    public PaginationResponse<List<ProductItemDTO>> findByFilter(ProductFilterRequest request) {
        Pageable pageable = PageRequest.of(request.getPageIndex(), request.getPageSize());
        Page<ProductItemDTO> products = productRepo.findByFilter(request.getProductName(),request.getCategoryId(),request.getStatus(),request.getColorId(), request.sizeId, pageable);
        return new PaginationResponse<>(products.getContent(), request.getPageIndex() + 1, products.getTotalPages() + 1, products.getTotalElements(),request.getPageSize());
    }

    @Override
    public void addProduct(ProductRequestDTO request) {
        Product product = request.toEntity();
        product.setCategory(categoryService.findCategoryById(request.getCategoryId()));
        product.setColor(colorService.findColorbyColorId(request.getColorId()));
        product.setSize(sizeService.findSizebySizeId(request.getColorId()));
        Image featureImage = filesStorageService.save(request.getFeatureImage());
        product.setFeatureImage(featureImage);
        List<Image> images = filesStorageService.saveAll(request.getImages());
        product.setImages(images);
        productRepo.save(product);
    }

    @Override
    public void editProduct(ProductRequestDTO request) {
        Product product = productRepo.findById(request.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
        request.updateEntity(product);
        product.setCategory(categoryService.findCategoryById(request.getCategoryId()));
        product.setColor(colorService.findColorbyColorId(request.getColorId()));
        product.setSize(sizeService.findSizebySizeId(request.getSizeId()));

        if (request.getFeatureImage() != null && !request.getFeatureImage().isEmpty()) {
            Image featureImage = filesStorageService.save(request.getFeatureImage());
            filesStorageService.delete(product.getFeatureImage());
            product.setFeatureImage(featureImage);
        }

        if (request.getDeleteImagesUrl() != null && !request.getDeleteImagesUrl().isEmpty()) {
            List<Image> deleteImages = imageRepo.findByImageUrl(request.getDeleteImagesUrl());
            filesStorageService.deleteAll(deleteImages);
            product.getImages().removeAll(deleteImages);
        }

        if (request.getImages() != null ) {
            List<Image> images = filesStorageService.saveAll(request.getImages());
            product.getImages().addAll(images);
        }
        productRepo.save(product);
    }

    @Override
    public ProductRequestDTO findById(Integer id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductRequestDTO.toDTO(product);
    }

    @Override
    public void changeStatus(Integer id, Status status) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStatus(status);
        productRepo.save(product);
    }

//    @Override
//    public ProductItemResponseDTO findProductItemByProductId(Integer memberId, Integer productId ) {
//        return productRepo.findProductItemByMemberIdAndProductId(memberId, productId);
//    }
//
//    public RatingDTO findTotalRatingAndAvgRatingByProductId(Integer productId) {
//        return productRepo.findTotalRatingAndAvgRatingByProductId(productId);
//    }
//
//    @Override
//    public PaginationResponse<List<RatingDetailResponseDTO>> findAllRatingByProductId(Integer productId, PaginationRequest request) {
//        Pageable pageable = PageRequest.of(request.getPageIndex(), request.getPageSize());
//        Page<RatingDetailResponseDTO> responseDTOS =  productRepo.findAllRatingDetailByProductId(productId,pageable);
//        return new PaginationResponse<>(responseDTOS.getContent(), request.getPageIndex() + 1
//                , responseDTOS.getTotalPages() + 1, responseDTOS.getTotalElements(),request.getPageSize());
//    }

}
