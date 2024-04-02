package com.capstone.app.entity.dto.dashboard.request;

import com.capstone.app.entity.Product;
import com.capstone.app.entity.Image;
import com.capstone.app.entity.type.Status;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDTO {

    private Integer productId;
    private Integer categoryId;
    private String categoryName;
    private MultipartFile featureImage;
    private String featureImageUrl;
    private MultipartFile[] images;
    private List<String> imageUrls;
    private List<String> deleteImagesUrl;
    private String productName;
    private Integer colorId;
    private String colorName;
    private Integer sizeId;
    private String sizeName;
    private Integer quantity;
    private Double basePrice;
    private Double deposit;
    private String description;
    private Status status;

    public Product toEntity() {
        return Product.builder()
                .productName(productName)
                .basePrice(basePrice)
                .deposit(deposit)
                .productDescription(description)
                .quantity(quantity)
                .status(Status.PENDING)
                .build();
    }

    public static ProductRequestDTO toDTO(Product product) {
        List<String> imageUrls = product.getImages().stream().map(Image::getImageUrl).toList();
        return ProductRequestDTO.builder()
                .productId(product.getProductId())
                .categoryId(product.getCategory().getCategoryId())
                .productName(product.getProductName())
                .colorId(product.getColor().getColorId())
                .sizeId(product.getSize().getSizeId())
                .featureImageUrl(product.getFeatureImage().getImageUrl())
                .imageUrls(imageUrls)
                .basePrice(product.getBasePrice())
                .deposit(product.getDeposit())
                .categoryName(product.getCategory().getCategoryName())
                .colorName(product.getColor().getColorName())
                .sizeName(product.getSize().getSizeName())
                .quantity(product.getQuantity())
                .description(product.getProductDescription())
                .status(product.getStatus())
                .build();
    }

    public void updateEntity(Product product) {
        product.setProductName(productName);
        product.setBasePrice(basePrice);
        product.setDeposit(deposit);
        product.setProductDescription(description);
        product.setQuantity(quantity);
    }
}
