package com.capstone.app.entity;

import com.capstone.app.entity.type.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "feature_image_id")
    private Image featureImage;

    @OneToMany
    private List<Image> images;

    @Column(name = "product_name", nullable = false, length = 100)
    @Size(max = 100)
    @NotBlank
    private String productName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "color_id")
    private Color color;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "size_id")
    private com.capstone.app.entity.Size size;

    @Column(name = "quantity", nullable = false)
    @Min(0)
    private Integer quantity;

    @Column(name = "base_price",nullable = false)
    private Double basePrice;

    @Column(name = "deposit",nullable = false)
    private Double deposit;

    @Column(name = "product_description", columnDefinition = "text")
    private String productDescription;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
